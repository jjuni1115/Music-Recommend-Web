package com.MrS.possible.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.MrS.possible.domain.YoutubeDT;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class YoutubeServiceImpl implements YoutubeService {
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
    public static YouTube youtube_type;
    // AIzaSyBqqNfLZ8URYN-W1jbReGlsyYMlP8wnTUI
    // AIzaSyABoT_EDIh6ydzpPeregb-5jNTe1rjzBTY
    private static final String API_KEY = "AIzaSyBqqNfLZ8URYN-W1jbReGlsyYMlP8wnTUI";  // Youtube API Key
    private static final String MAX_RESULT = "3"; // When you Search by Youtube API, Number of Videos to get

    private static double put_youtubeDT(Iterator<Video> iteratorSearchResults, YoutubeDT youtubeDT){
        System.out.println("+====================================================+");

        Video singleVideo = iteratorSearchResults.next();

        // video type check & set Single video's data to youtubeDT class field
        if (singleVideo.getKind().equals("youtube#video")) {
            youtubeDT.setViewCount(singleVideo.getStatistics().getViewCount());
            youtubeDT.setDuration(singleVideo.getContentDetails().getDuration());
        }

        // video length : more than 1hour || more then 6min : It's not a Music
        int T = youtubeDT.getDuration().indexOf("T");
        int M = youtubeDT.getDuration().indexOf("M");
        if (youtubeDT.getDuration().charAt(0) != 'P'
                || Integer.parseInt(youtubeDT.getDuration().substring(T+1, M)) >= 6){
            return 1.0;
        }
        return youtubeDT.getViewCount().doubleValue(); // return viewCount
    }

    // Get 1 video's detail Data : viewCount, title, Duration... What ever you want
    // Get videoId -> return viewCount
    public double get_youtubeDT(String videoId) {
        YoutubeDT youtubeDT = new YoutubeDT();
        double viewCount = 0;
        try{
            youtubeDT.setVideoID(videoId);

            youtube_type = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer(){
                public void initialize(HttpRequest request) throws IOException {

                }
            }).setApplicationName("youtube-video-duration-get").build();

            // Youtube API's List type
            YouTube.Videos.List videos = youtube_type.videos().list("id, statistics, contentDetails");
            videos.setKey(API_KEY); // set API Key
            videos.setId(StringtoJsontoValue(videoId, "videoId"));  // set videoId
            videos.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);  // 1 video

            // Get Request & processing
            List<Video> videoList = videos.execute().getItems();
            if (videoList != null){
                viewCount = put_youtubeDT(videoList.iterator(), youtubeDT);  // Transfer Data to put youtubeDT
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
        return viewCount;
    }

    // Impl : ""key" : "data"" -> make JSON format {"key" : "data"} -> json.getString("key") = value
    private String StringtoJsontoValue(String data, String key){
        JSONObject jObject = new JSONObject("{" + data + "}");

        return jObject.getString(key);  // return "value"
    }

    // check ViewCount & get most view count 'VideoID'
    private String mostview(String[] videoId, String[] thumbnailPath, YoutubeDT youtubedt){
        // get Most View VideoID
        double[] result = new double[Integer.parseInt(MAX_RESULT)];
        double temp = 0.0;
        int answer = 0;
        for (int i=0; i<3; i++){
            result[i] = get_youtubeDT(videoId[i]);
            if (result[i] > temp){
                temp = result[i];
                answer = i;
            }
        }

        // Parsing & YoutubeDT.setThumbnailPath() field
        youtubedt.setThumbnailPath(StringtoJsontoValue(thumbnailPath[answer], "url"));

        return StringtoJsontoValue(videoId[answer], "videoId"); // return videoId
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
    public String search(YoutubeDT youtubedt) throws IOException{
        // getAPI_URL : Make URL format and set Options like maxResult, API_KEY insert, Query(artist-title)
        URL url = new URL(getAPI_URL(youtubedt));  // get url
        HttpURLConnection con = (HttpURLConnection) url.openConnection();  // openConnection
        con.setRequestMethod("GET");

        //BufferReader, UTF-8
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuffer response = new StringBuffer();

        String input;
        // videoId, thumbnailPath to deal with YoutubeDT field name
        String[] videoId = new String[Integer.parseInt(MAX_RESULT)]; // Array[String]
        String[] thumbnailPath = new String[Integer.parseInt(MAX_RESULT)];

        // read Request & Parsing based on String <- Because Request video's json format is redundant
        int tmp = 0;
        while((input = br.readLine()) != null){
            response.append(input);
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

        // Impl mostview() -> YoutubeDT.setVideoID() = Most View count 'videoId' is into YoutubeDT.videoId field.
        youtubedt.setVideoID(mostview(videoId, thumbnailPath, youtubedt));
        return youtubedt.getVideoID();

    }

//     O AUTH 2.0 version, not use.


//    // https://stackoverrun.com/ko/q/11923791
//    private static final String CLIENT_SECRETS= "/client_secret.json";  // absolute path
//    private static final Collection<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/youtube.force-ssl");
//    private static final String APPLICATION_NAME = "music2";
//    private static final JsonFactory JSON_FACTORY_DF = JacksonFactory.getDefaultInstance();


//    public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
//        // load Client Secrets
//
//        // read CLIENT_SECRETS = /Secret_key.json file by absolute root.
//        InputStream in = YoutubeService.class.getResourceAsStream(CLIENT_SECRETS);
//
//        // Reading Json Type file to mapping GoogleClientSecrets
//        // https://javafreak.tistory.com/258
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY_DF, new InputStreamReader((in), StandardCharsets.UTF_8));
//
//        //Build flow and trigger user request
//        GoogleAuthorizationCodeFlow Flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY_DF, clientSecrets, SCOPES).build();
//
//        System.out.println("authorize : 4");
//        Credential credential = new AuthorizationCodeInstalledApp(Flow, new LocalServerReceiver()).authorize("502679761590-g51gl1pf25pb092g7mr4gtqv9nnhmmok.apps.googleusercontent.com");
//        System.out.println("authorize : 5");
//        return credential;
//    }
//
//    //build & return API client Service.
//    public static YouTube getService() throws GeneralSecurityException, IOException{
//        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        Credential credential = authorize(httpTransport);
//        System.out.println("getService : 3");
//        return new YouTube.Builder(httpTransport, JSON_FACTORY_DF,
//                credential).setApplicationName(APPLICATION_NAME).build();
//    }

//    public YoutubeDT search(YoutubeDT youtubedt) throws GeneralSecurityException, IOException, GoogleJsonResponseException{
//        System.out.println("YoutubeServiceImpl:" + youtubedt.getTitle());
//        YouTube gotService = getService();
//        System.out.println("after get Service");
//        // execute API request
//        YouTube.Search.List request = gotService.search().list("snippet");
//        SearchListResponse response = request.setMaxResults(10L).setQ("surfing").execute();
//        System.out.println(response);
//        return youtubedt;
//        // return youtubedt;YoutubeDT
//    }
}
