package com.MrS.possible.Service;

import com.MrS.possible.dao.MusicDao;
import com.MrS.possible.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServicelmpl implements MusicService {
    @Autowired
    private MusicDao musicDao;

    public int insert(List<Music> musicList){

        return musicDao.insert(musicList);
    }

    public List<result> search(String keyword){
        return musicDao.search(keyword);
    }

    public List<result> search_artist(String keyword){
        return musicDao.search_artist(keyword);
    }

    public int insert_playlist(add_playlist keyword){

        return musicDao.insert_playlist(keyword);
    }

    public List<result> load(load_pl keyword){

        return musicDao.load(keyword);
    }

    public int create_playlist(playlist keyword){

        return musicDao.create_playlist(keyword);
    }

    public List<String> load_playlist(String keyword){
        return musicDao.load_playlist(keyword);
    }

    public int add_sharelist(NewPlaylist keyword){
        return musicDao.add_sharelist(keyword);
    }

    public List<String> load_sharelist(){
        return musicDao.load_sharelist();
    }

    public List<result> sharelist(String keyword){
        return musicDao.sharelist(keyword);
    }
}
