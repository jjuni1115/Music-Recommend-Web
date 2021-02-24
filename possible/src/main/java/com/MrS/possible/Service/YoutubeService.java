package com.MrS.possible.Service;

import com.MrS.possible.dao.YoutubeDao;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.MrS.possible.domain.YoutubeDT;

import java.io.IOException;
import java.security.GeneralSecurityException;

@FunctionalInterface
public interface YoutubeService{
    public void searchDo();

}
