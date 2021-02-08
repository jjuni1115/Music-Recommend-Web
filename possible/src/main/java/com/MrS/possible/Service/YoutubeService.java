package com.MrS.possible.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.MrS.possible.domain.YoutubeDT;

import java.io.IOException;
import java.security.GeneralSecurityException;

@FunctionalInterface
public interface YoutubeService {

    public YoutubeDT search(YoutubeDT youtubedt) throws GeneralSecurityException, IOException, GoogleJsonResponseException;
}
