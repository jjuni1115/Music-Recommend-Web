package com.MrS.possible.Service;

import com.MrS.possible.dao.MemberDao;
import com.MrS.possible.dao.YoutubeDao;
import com.MrS.possible.dao.YoutubeDaoImpl;
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

import lombok.SneakyThrows;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class YoutubeServiceImpl implements YoutubeService{
//    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
//    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
//    public static YouTube youtube_type;
//    YoutubeApiConf YoutubeConfig = YoutubeApiConf.getInstance();  //Singleton getInstance
//    private final String API_KEY = YoutubeConfig.getAPI(2);  // Youtube API Key (parameter 1 or 2)
//    public final String MAX_RESULT = YoutubeConfig.getMaxCount(); // When you Search by Youtube API, Number of Videos to get

    @Autowired
    private YoutubeDao youtubeDao;
    private MemberDao memberDao;

    public YoutubeServiceImpl(YoutubeDao youtubeDao, MemberDao memberDao) {
       this.youtubeDao = youtubeDao;
       this.memberDao = memberDao;
    }

    public void searchDo(){
        SearchDo SD = new SearchDo(youtubeDao, memberDao);
        SD.start();

        try{
            SD.join();          //Main Thread가 searchDo Thread 위에 업혀서 대기

            // youtube Search API 작업이 끝나면 아래 데이터 형식에 맞추어 준비한 후 session.setAttribute()로 페이지 넘겨줌.
            // session은 youtubeDaoImpl 객체에 session을 저장해두었음  <- Controller에서 DaoImpl() 객체 생성 시 sesison 넘겨서 초기화
            // YoutubeDT class > Y_M class : set necessary Fields
            YoutubeDT.Y_M yt_music = new YoutubeDT.Y_M(memberDao.getMember().getId(), memberDao.getMember().getAccount(),
                    youtubeDao.getYoutubedt().getArtist(), youtubeDao.getYoutubedt().getTitle(), youtubeDao.getYoutubedt().getVideoID());

            youtubeDao.getSession().setAttribute("youtubedt", yt_music); // 2021.01.19
        }catch(InterruptedException e){
            e.printStackTrace();
        }
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
