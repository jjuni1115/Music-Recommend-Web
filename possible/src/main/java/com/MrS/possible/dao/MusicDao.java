package com.MrS.possible.dao;

import com.MrS.possible.domain.Music;
import com.MrS.possible.domain.add_playlist;
import com.MrS.possible.domain.result;
import com.google.api.client.json.Json;

import java.util.List;

public interface MusicDao {
    public int insert(List<Music> musicList);
    public List<result> search(String keyword);
    public int insert_playlist(add_playlist keyword);
    public List<result> load(String keyword);

}
