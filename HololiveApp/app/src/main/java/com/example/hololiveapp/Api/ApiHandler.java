package com.example.hololiveapp.Api;

import android.content.Intent;
import com.example.hololiveapp.models.VideoDataModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * This class is used to obtain video ur/ video data from API
 */
public class ApiHandler {

    private List<VideoDataModel> videolist;

    private String videoLocation;

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
                URL url = new URL("http://10.0.2.2:8080/holotopia_backend_war/hololive-members/"+ vtuberID +"/videos");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
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
                String youtubeId = intent.getStringExtra("id");
                String apiEndpoint = "http://10.0.2.2:8080/holotopia_backend_war/videos/"+ youtubeId +"/url";
                URL url = new URL(apiEndpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
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


}
