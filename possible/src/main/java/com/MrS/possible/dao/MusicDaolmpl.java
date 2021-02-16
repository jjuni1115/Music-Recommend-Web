package com.MrS.possible.dao;

import com.MrS.possible.domain.*;
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

    public List<result> search_artist(String keyword) {

        return sqlSession.selectList("music.search_artist",keyword);
    }

    public int insert_playlist(add_playlist keyword){
        System.out.println(keyword);

        return sqlSession.insert("music.playlist",keyword);
    }

    public List<result> load(load_pl keyword) {

        return sqlSession.selectList("music.load",keyword);
    }

    public int create_playlist(playlist keyword){
        System.out.println(keyword);
        return sqlSession.insert("music.create_playlist",keyword);
    }

    public List<String> load_playlist(String keyword){
        return sqlSession.selectList("music.load_playlist",Integer.parseInt(keyword));
    }

    public int add_sharelist(playlist keyword){
        System.out.println(keyword);
        return sqlSession.insert("music.add_sharelist",keyword);
    }
}
