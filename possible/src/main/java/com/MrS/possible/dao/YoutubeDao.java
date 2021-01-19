package com.MrS.possible.dao;

import com.MrS.possible.domain.RecResult;
import com.MrS.possible.domain.YoutubeDT;

import java.util.List;

public interface YoutubeDao {
    public void videoidInsert(String videoId, YoutubeDT youtubedt);
    public List<RecResult>[] rec(String videoID);
}
