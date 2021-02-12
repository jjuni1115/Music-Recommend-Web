package com.MrS.possible.dao;

import com.MrS.possible.domain.Music;
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
    public void videoidInsert(YoutubeDT youtubedt) {
        YoutubeDT youtubedt2 = sqlSession.selectOne(Namespace + "selectMusic", youtubedt); //DB에 있는 곡이면 행 추출
        if(youtubedt2 == null) return;  // 검색한 곡이 DB에 없는 곡이었다면 return
        sqlSession.insert(Namespace + "searchResult", youtubedt);  // music에 모든 data 들어가면 주석 풀기
    }

    // 노래와 관련있는 모든 사용자의 플레이 리스트 기준 & 노래를 포함한 플레이 리스트 기준 추천 곡 return
    @Override
    public List<RecResult>[] rec(String videoID) {
        List<RecResult> arr[] = new List[2]; // List <RecResult> array[]
        arr[0] = sqlSession.selectList(Namespace + "userRecommend", videoID); // by user
        arr[1] = sqlSession.selectList(Namespace + "playlistRecommend", videoID); // by playlist
        // Two of Results are Return Together <- Using  List<>[]
        System.out.println(arr[1].size());
        return arr;
    }

    @Override
    public String checkGetvideoID(YoutubeDT youtubedt) {
        return sqlSession.selectOne(Namespace + "getVideoId", youtubedt);
    }


}
