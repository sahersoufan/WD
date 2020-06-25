package sample.proccess;


import sample.Objects4GUI;
import sample.threads.thread4StartDownloading;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Downloading implements Objects4GUI {
    private Spider spider = new Spider();
    private String URL="http://www.guimp.com/", saveLocation="D:\\";
    private thread4StartDownloading th4SD ;

    public void setTh4SD(thread4StartDownloading th4SD) {
        this.th4SD = th4SD;
    }

    public Downloading(){
        spider.setDownload(this);
        helper.setD(this);
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    //set FUll size text Label
    public void setFullSizeLabel(String fullSize) throws Exception {
        helper.setFullSizeLabel(fullSize);
    }

    //set title of info gui
    public void setTitle4InfoGui(String mainTitle) throws Exception {
        helper.setTitle4InfoGui(mainTitle);
    }

    // Update download size label
    public void setUpdateDownloadingSizeLabel(String updateSize){
            helper.setUpdateDownloadingSizeLabel(updateSize);
    }

    //set origin path of downloading folder
    public void setOriginPathFolder(String originPath) throws InterruptedException {
        helper.setOriginPathFolder(originPath);
    }

    //Run information GUI
    public void RunInformationGui() throws Exception {
        helper.RunInformationGUI();
    }




    //Start method
    public void Start() throws Exception {
        spider.setURL(URL);
        spider.setSaveLocation(saveLocation);
        RunInformationGui();

        spider.initPageFile();
        spider.InitSpiderProp();
        helper.update();
        spider.StartSpiderThreads();
    }

    // restart Downloading
    public void StartDownloadingAgain(){
        new Thread(th4SD).start();
    }





    // send an order to spider to stop the download operation
    public  void CancelDownloading(){
        spider.setCancelStatementInfoGui(true);
    }

    // send order to spider to pause the download operation
    public void PauseDownloading(){
        spider.PauseDownloading();
    }

    // send order to spider to resume the download operation
    public void ResumeDownloading() throws IOException, InterruptedException {
        spider.ResumeDownloading();
    }

    //open file location that we download web site in it
    public void OpenFileLocation() throws IOException {
        spider.OpenFileLocation();
    }

    //get downloading size
    public long getDownloadingSize() throws IOException {
        return spider.getDownloadingSize();
    }

    // Run Pause Gui
    public void RunPauseGui() throws Exception {
        helper.RunPauseGui();
    }

    // send orders to download to stop for loop
    public void SendOrderToDownloadToStopDownloading(){
        th4SD.StopDownloading();
    }

    }


