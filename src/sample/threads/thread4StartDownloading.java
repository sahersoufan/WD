package sample.threads;

import sample.proccess.Downloading;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class thread4StartDownloading implements Runnable {
    private List<String> ws;
    private String SaveLocation;
    private boolean stopDownloading = false;

    // send this to download
    public void SendThread4StartDownloading(Downloading d){
        d.setTh4SD(this);
    }

    public void seturl(List<String> w, String s){
        ws = new ArrayList<>(w);
        SaveLocation = s;
    }

    @Override
    public void run() {
        for(int i = 0; i < ws.size(); i++){

            if(stopDownloading){
                removeFirstUrlWSList();
                stopDownloading = false;
            }

            Downloading download = new Downloading();
            download.setSaveLocation(SaveLocation);
            download.setURL(ws.get(i));
            SendThread4StartDownloading(download);
            try {
                download.Start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(stopDownloading) break;
            ws.remove(i);
            i--;
        }

    }

    //Stop Downloading
    public void StopDownloading(){
        stopDownloading = true;
    }

    //remove first url from the ws list
    private void removeFirstUrlWSList(){
        ws.remove(0);
    }
}
