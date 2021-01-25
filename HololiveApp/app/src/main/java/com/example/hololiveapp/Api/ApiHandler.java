package com.example.hololiveapp.Api;

import android.content.Intent;
import com.example.hololiveapp.models.VideoDataModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to obtain video ur/ video data from API
 */
public class ApiHandler {

    private List<VideoDataModel> videolist;

    private String videoLocation;

    private String jsonToken;

    public String getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(String videoLocation) {
        this.videoLocation = videoLocation;
    }

    public List<VideoDataModel> getVideolist() {
        return videolist;
    }

    public void setVideolist(List<VideoDataModel> videolist) {
        this.videolist = videolist;
    }

    /**
     * Gets thumbnail and videoID from API
     * @param vtuberID used to get videolist from specific Vtuber
     */
    public void getApiData(String vtuberID){
        Thread apiThr = new Thread(() -> {
            try {
                login();
                URL url = new URL("http://10.0.2.2:8080/holotopia_backend_war/hololive-members/"+ vtuberID +"/videos");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty ("Authorization", "Bearer "+jsonToken);
                conn.connect();
                ObjectMapper mapper = new ObjectMapper();
                String inputline = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((inputline = reader.readLine()) != null) {
                    response.append(inputline);
                }
                reader.close();
                videolist = mapper.readValue(response.toString(), new TypeReference<List<VideoDataModel>>(){});
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        apiThr.start();
    }

    /**
     * Retrieves the url located at the holotopia endpoint
     * @param intent is used to obtain value that was passed from other activity
     */
    public void getVideoLocationFromApi(Intent intent){
        Thread getVideoThr = new Thread(() -> {
            try {
                login();
                String youtubeId = intent.getStringExtra("id");
                String apiEndpoint = "http://10.0.2.2:8080/holotopia_backend_war/videos/"+ youtubeId +"/url";
                URL url = new URL(apiEndpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty ("Authorization", "Bearer "+jsonToken);
                conn.connect();
                String inputline = "";
                ObjectMapper mapper = new ObjectMapper();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((inputline = reader.readLine()) != null) {
                    response.append(inputline);
                }
                videoLocation = response.toString();
                reader.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        });
        getVideoThr.start();

    }


    public void login() throws IOException {

        String loginCredentials = "username=app&password=app";
        byte[] postData = loginCredentials.getBytes( StandardCharsets.UTF_8 );
        int postDataLength = postData.length;
        String request = "http://10.0.2.2:8080/holotopia_backend_war/login";
        URL url = new URL( request );
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write( postData );
        }
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            jsonToken = conn.getHeaderField("json-token");
                } else {
                    System.out.println("POST request not worked");
                }
    }





}
