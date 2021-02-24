package com.MrS.possible.Service;

import com.MrS.possible.dao.MemberDao;
import com.MrS.possible.dao.YoutubeDao;
import com.MrS.possible.domain.YoutubeDT;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchDo extends Thread{
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
    public static YouTube youtube_type;
    YoutubeApiConf YoutubeConfig = YoutubeApiConf.getInstance();  //Singleton getInstance
    private final String API_KEY = YoutubeConfig.getAPI(2);  // Youtube API Key (parameter 1 or 2)
    public final String MAX_RESULT = YoutubeConfig.getMaxCount(); // When you Search by Youtube API, Number of Videos to get

    @Autowired
    private YoutubeDao youtubeDao;
    private MemberDao memberDao;

    public SearchDo(YoutubeDao youtubeDao ,MemberDao member){
        this.youtubeDao = youtubeDao;
        this.memberDao = member;
    }

    @Override
    public void run() {
        YoutubeDT youtubedt = youtubeDao.getYoutubedt();
        System.out.println("Thread start!");
        try{
            search(youtubedt);

            youtubeDao.videoidInsert(youtubeDao.getYoutubedt());
        }catch(IOException e){
            e.printStackTrace();
        }
        catch(InterruptedException e1){
            e1.printStackTrace();
        }
    }


    private double[] put_youtubeDT(List<Video> videoList){  // Most viewCount video Index return
        System.out.println("+====================================================+");

        BigInteger mostView = new BigInteger("0");
        Video singleVideo;
        int idx = 0;  int videoIdx = 0;
        double[] result = new double[2];
        Iterator<Video> VL = videoList.iterator();  //iterator loop
        while(VL.hasNext()) {
            singleVideo = VL.next();

            BigInteger viewCount = singleVideo.getStatistics().getViewCount();
            String duration = singleVideo.getContentDetails().getDuration();

            int T = duration.indexOf("T");   int M = duration.indexOf("M");
            // video length : more than 1hour || more then 6min : It's not a Music
            if (duration.charAt(0) != 'P' ){
                continue;
            }// || Integer.parseInt(duration.substring(T+1, M)) >= 6

            if (result[1] < viewCount.doubleValue()) {
                result[0] = idx;
                result[1] = viewCount.doubleValue();
            }
            idx++;
        }

        return result;                  // return [Index, viewCount]
    }

    // Get 1 video's detail Data : viewCount, title, Duration... What ever you want
    // Get videoId -> return viewCount
    public double[] get_youtubeDT(String[] videoId) {
        double[] idxViewCount = new double[2];
        try{
            youtube_type = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer(){
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-video-duration-get").build();

            // Youtube API's List type
            YouTube.Videos.List videos = youtube_type.videos().list("id, statistics, contentDetails");
            videos.setKey(API_KEY); // set API Key

            List<Video> videoList = new ArrayList<>();
            for(int i=0; i<Integer.parseInt(MAX_RESULT); i++){
                YoutubeDT youtubeDT = new YoutubeDT();
                youtubeDT.setVideoID(videoId[i]);

                videos.setId(StringtoJsontoValue(videoId[i], "videoId"));  // set videoId
                videos.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);  // 1 video

                // Get Request & processing
                videoList.addAll(videos.execute().getItems());  // add video to videoList
            }

            if (videoList.size() > 0){
                idxViewCount = put_youtubeDT(videoList);  // Transfer Data to put youtubeDT
            }

        }
        catch(GoogleJsonResponseException e){
            System.out.println("Json Resp Exception :" + e.getDetails().getCode() + ":" +
                    e.getDetails().getMessage());
        }
        catch(IOException e){
            System.out.println("IOException :" + e.getCause() + e.getMessage());
        }
        catch(Throwable t){
            t.printStackTrace();
        }
        System.out.println("viewCount : " + idxViewCount[1]);

        return idxViewCount;
    }

    // Impl : ""key" : "data"" -> make JSON format {"key" : "data"} -> json.getString("key") = value
    private String StringtoJsontoValue(String data, String key){
        JSONObject jObject = new JSONObject("{" + data + "}");

        return jObject.getString(key);  // return "value"
    }

    // check ViewCount & get most view count 'VideoID'
    private String mostview(String[] videoId, String[] thumbnailPath, YoutubeDT youtubedt){
        // get Most View VideoID
        double[] result;
        result = get_youtubeDT(videoId);

        // Parsing & YoutubeDT.setThumbnailPath() field
        youtubedt.setThumbnailPath(StringtoJsontoValue(thumbnailPath[(int)result[0]], "url"));
        youtubedt.setViewCount(BigInteger.valueOf((long) result[1]));   //double type -> BigInteger type

        return StringtoJsontoValue(videoId[(int)result[0]], "videoId"); // return videoId
    }

    // Make URL, Setting
    private String getAPI_URL(YoutubeDT youtubedt) throws UnsupportedEncodingException {
        String apiurl = "https://www.googleapis.com/youtube/v3/search";
        apiurl += "?key=" + API_KEY;
        apiurl += "&part=snippet&type=video&videoDuration<6&maxResults=" + MAX_RESULT + "&videoEmbeddable=true";
        apiurl += "&q=" + URLEncoder.encode(youtubedt.getArtist() + " " + youtubedt.getTitle(), "UTF-8");
        // &q= 이후 query 준비

        return apiurl;
    }

    // youtube search : (artist-title) format & return json type data & Parsing & processing)
    public YoutubeDT search(YoutubeDT youtubedt) throws IOException, InterruptedException{
        // getAPI_URL : Make URL format and set Options like maxResult, API_KEY insert, Query(artist-title)
        URL url = new URL(getAPI_URL(youtubedt));  // get url
        HttpURLConnection con = (HttpURLConnection) url.openConnection();  // openConnection
        con.setRequestMethod("GET");

        //BufferReader, UTF-8
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        // StringBuffer response = new StringBuffer();

        String input;
        // videoId, thumbnailPath to deal with YoutubeDT field name
        String[] videoId = new String[Integer.parseInt(MAX_RESULT)]; // Array[String]
        String[] thumbnailPath = new String[Integer.parseInt(MAX_RESULT)];

        // read Request & Parsing based on String <- Because Request video's json format is redundant
        int tmp = 0;
        while((input = br.readLine()) != null){
            if (input.contains("\"videoId\": ")){
                videoId[tmp] = input;
            }
            else if(input.contains("hqdefault.jpg\",")){
                thumbnailPath[tmp] = input;
                tmp += 1;
            }
        }

        br.close();  //buffer close

        for(int i=0; i<Integer.parseInt(MAX_RESULT); i++) {
            System.out.println(videoId[i]);
        }

        youtubeDao.setYoutubedt(youtubedt);

        // Impl mostview() -> YoutubeDT.setVideoID() = Most View count 'videoId' is into YoutubeDT.videoId field.
        youtubedt.setVideoID(mostview(videoId, thumbnailPath, youtubedt));

        Thread.sleep(4000);  // Use :  When Test Thread

        return youtubedt;  // return youtubedt : videoId, title, artist, viewCount

    }
}
