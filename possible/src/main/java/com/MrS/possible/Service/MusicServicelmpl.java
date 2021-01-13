package com.MrS.possible.Service;

import com.MrS.possible.dao.MusicDao;
import com.MrS.possible.domain.Music;
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
}
