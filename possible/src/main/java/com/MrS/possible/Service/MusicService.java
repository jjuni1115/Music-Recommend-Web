package com.MrS.possible.Service;

import com.MrS.possible.domain.Music;
import com.MrS.possible.domain.result;

import java.util.List;

public interface MusicService {
    public int insert(List<Music> musicList);
    public List<result> search(String keyword);
    public int insert_playlist(result keyword);
}
