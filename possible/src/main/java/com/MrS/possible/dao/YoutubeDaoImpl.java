package com.MrS.possible.dao;

import com.MrS.possible.domain.YoutubeDT;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class YoutubeDaoImpl implements YoutubeDao{
    @Autowired
    @Qualifier("sqlSession")
    SqlSession sqlSession;

    private static final String Namespace = "memberMapper.";

    @Override
    public void temp(String videoId, YoutubeDT youtubedt) {
        // sql Session videoId_insert
    }

    // 나중에 db에 노래 들어가면 유튜브 노래 검색 시 videoid 받은 거 videoid 컬럼 업데이트하기

}
