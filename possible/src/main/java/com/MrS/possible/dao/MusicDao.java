package com.MrS.possible.dao;

import com.MrS.possible.domain.*;

import java.util.HashMap;
import java.util.List;

public interface MusicDao {
    public int insert(List<Music> musicList);
    public List<result> search(String keyword);
    public List<result> search_artist(String keyword);
    public int insert_playlist(add_playlist keyword);
    public Object[] load(load_pl keyword);
    public int create_playlist(playlist keyword);
    public List<String> load_playlist(String keyword);
    public int add_sharelist(NewPlaylist keyword);
    public List<String> load_sharelist();
    public List<result> sharelist(String keyword);
    public int delete_music(add_playlist keyword);
    public void toggle_share(String[] listName);
    public List<result> refresh(load_pl keyword);

}
