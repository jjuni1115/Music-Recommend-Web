package com.MrS.possible.dao;

import com.MrS.possible.domain.Music;
import com.MrS.possible.domain.add_playlist;
import com.MrS.possible.domain.result;
import com.google.api.client.json.Json;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicDaolmpl implements MusicDao{
    @Autowired
    @Qualifier("sqlSession")

    private SqlSession sqlSession;
    public int insert(List<Music> musicList){
        System.out.println(musicList);
        return sqlSession.insert("music.insert",musicList);
    }

    @Override
    public List<result> search(String keyword) {
        return sqlSession.selectList("music.search",keyword);
    }

    public int insert_playlist(add_playlist keyword){
        System.out.println(keyword);

        return sqlSession.insert("music.playlist",keyword);
    }
}
