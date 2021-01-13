package com.MrS.possible.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.MrS.possible.domain.YoutubeDT;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface YoutubeService {

    public String search(YoutubeDT youtubedt) throws GeneralSecurityException, IOException, GoogleJsonResponseException;
}
