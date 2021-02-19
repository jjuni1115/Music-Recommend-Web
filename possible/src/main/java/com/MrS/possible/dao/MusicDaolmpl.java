package com.MrS.possible.dao;

import com.MrS.possible.domain.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MusicDaolmpl implements MusicDao{
    @Autowired
    @Qualifier("sqlSession")

    private SqlSession sqlSession;
    private final static String Namespace = "music.";
    public int insert(List<Music> musicList){
        System.out.println(musicList);
        return sqlSession.insert(Namespace + "insert",musicList);
    }

    @Override
    public List<result> search(String keyword) {

        return sqlSession.selectList(Namespace + "search",keyword);
    }

    public List<result> search_artist(String keyword) {

        return sqlSession.selectList(Namespace + "search_artist",keyword);
    }

    public int insert_playlist(add_playlist keyword){
        System.out.println(keyword);

        return sqlSession.insert(Namespace + "playlist",keyword);
    }

    public Object[] load(load_pl keyword) {
        List<result> musicList = sqlSession.selectList(Namespace + "load", keyword);

        boolean is_share = sqlSession.selectOne(Namespace + "load2",keyword);
        System.out.println(is_share);
        HashMap<List<result>, Boolean> map = new HashMap<>();
        Object[] objects = new Object[2];
        objects[0] = musicList; objects[1] = is_share;
        map.put(musicList, is_share);
        return objects;
    }

    public int create_playlist(playlist keyword){
        System.out.println(keyword);
        return sqlSession.insert(Namespace + "create_playlist",keyword);
    }

    public List<String> load_playlist(String keyword){
        return sqlSession.selectList(Namespace + "load_playlist",Integer.parseInt(keyword));
    }

    public int add_sharelist(NewPlaylist keyword){
        System.out.println(keyword);
        return sqlSession.insert(Namespace + "add_sharelist",keyword);
    }

    public List<String> load_sharelist(){
        return sqlSession.selectList("music.load_sharelist");
    }

    public List<result> sharelist(String keyword){
        return sqlSession.selectList("music.sharelist",keyword);
    }

    @Override
    public void toggle_share(String[] listName) {
        class tmpClass{
            public String list_name;
            public String user_ID;
        }
        tmpClass tmpcls = new tmpClass();
        tmpcls.list_name = listName[0];
        tmpcls.user_ID = listName[1];
        sqlSession.update(Namespace + "toggleShare", tmpcls);
        tmpcls = null;
    }
}
