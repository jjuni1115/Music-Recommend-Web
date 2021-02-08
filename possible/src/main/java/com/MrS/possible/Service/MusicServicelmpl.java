package com.MrS.possible.Service;

import com.MrS.possible.dao.MusicDao;
import com.MrS.possible.domain.Music;
import com.MrS.possible.domain.add_playlist;
import com.MrS.possible.domain.result;
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

    public List<result> load(String keyword){

        return musicDao.load(keyword);
    }
}
