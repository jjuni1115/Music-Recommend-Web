package com.MrS.possible.dao;

import com.MrS.possible.domain.RecResult;
import com.MrS.possible.domain.YoutubeDT;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface YoutubeDao {
    public void setYoutubedt(YoutubeDT youtubedt);
    public void setYoutubedt(YoutubeDT youtubedt, HttpSession session);
    public YoutubeDT getYoutubedt();
    public HttpSession getSession();
    public void videoidInsert(YoutubeDT youtubedt);
    public List<RecResult>[] rec(String videoID);
    public String checkGetvideoID(YoutubeDT youtubedt);
}
