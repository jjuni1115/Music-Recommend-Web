package com.MrS.possible.Service;

import com.MrS.possible.domain.*;

import java.util.List;

public interface MusicService {
    public int insert(List<Music> musicList);
    public List<result> search(String keyword);
    public List<result> search_artist(String keyword);
    public int insert_playlist(add_playlist keyword);
    public List<result> load(load_pl keyword);
    public int create_playlist(playlist keyword);
    public List<String> load_playlist(String keyword);
}
