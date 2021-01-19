package com.MrS.possible.dao;

import com.MrS.possible.domain.RecResult;
import com.MrS.possible.domain.YoutubeDT;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class YoutubeDaoImpl implements YoutubeDao{
    @Autowired
    @Qualifier("sqlSession")
    SqlSession sqlSession;

    private static final String Namespace = "youtubeMapper.";

    // When you search music, music.videoid column will update immediately
    @Override
    public void videoidInsert(String videoId, YoutubeDT youtubedt) {
        youtubedt.setVideoID(videoId);
//        sqlSession.insert(Namespace + "searchResult", youtubedt);  // music에 모든 data 들어가면 주석 풀기
    }

    // 노래와 관련있는 모든 사용자의 플레이 리스트 기준 & 노래를 포함한 플레이 리스트 기준 추천 곡 return
    @Override
    public List<RecResult>[] rec(String videoID) {
        List<RecResult> arr[] = new List[2]; // List <RecResult> array[]

        arr[0] = sqlSession.selectList(Namespace + "userRecommend", videoID); // by user
        arr[1] = sqlSession.selectList(Namespace + "playlistRecommend", videoID); // by playlist

        // Two of Results are Return Together <- Using  List<>[]
        return arr;
    }

    // 나중에 db에 노래 들어가면 유튜브 노래 검색 시 videoid 받은 거 videoid 컬럼 업데이트하기

}
