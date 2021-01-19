package com.MrS.possible.Service;

import com.MrS.possible.domain.Member;
import com.MrS.possible.domain.Music;
import com.MrS.possible.domain.RecResult;
import com.MrS.possible.domain.YoutubeDT;

import java.util.List;

public interface PythonService {
    public List<RecResult>[] recommend(Member member, YoutubeDT youtubedt);

}
