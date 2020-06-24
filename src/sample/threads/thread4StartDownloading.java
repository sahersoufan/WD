package sample.threads;

import sample.proccess.Downloading;

import java.util.ArrayList;
import java.util.List;

public class thread4StartDownloading implements Runnable {
    List<String> ws;
    String SaveLocation;
    public void seturl(List<String> w, String s){
        ws = new ArrayList<>(w);
        SaveLocation = s;
    }

    @Override
    public void run() {
        for(String w : ws){

            Downloading download = new Downloading();
            download.setSaveLocation(SaveLocation);
            download.setURL(w);
            try {
                download.Start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
