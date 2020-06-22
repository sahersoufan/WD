package sample.threads;

import sample.process.Downloading;

public class thread4StartDownloading implements Runnable {
    String ws;
    String SaveLocation;
    public void seturl(String w,String s){
        ws = w;
        SaveLocation = s;
    }

    @Override
    public void run() {
        Downloading download = new Downloading();
        download.setSaveLocation(SaveLocation);
        download.setURL(ws);
        try {
            download.Start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
