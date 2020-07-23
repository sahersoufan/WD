package sample.proccess;


import sample.Objects4GUI;
import sample.threads.thread4StartDownloading;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Downloading implements Objects4GUI {
    private Spider spider = new Spider();
    private String URL="http://www.guimp.com/", saveLocation="D:\\";
    private thread4StartDownloading th4SD ;
    private List<String> Types;
    private int depth;

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

    public void setTypes(List<String> t){Types = new ArrayList<>(t);}

    public void setDepth(int d){depth = d;}

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
        spider.setTypes(Types);
        spider.setDepth(depth);
        spider.initPageFile();
        StartSpiderWorking();


    }

    //Start spider working
    public void StartSpiderWorking() throws Exception{
        try {
            RunInformationGui();
            spider.InitSpiderProp();
            helper.update();
            spider.StartSpiderThreads();

        }catch (IOException e){
            SendOrderToTheThreadsToStopDownloading();
            Process process = java.lang.Runtime.getRuntime().exec("ping www.google.com");
            int x = process.waitFor();
            if(x == 0){
                UnValidUrlMessage(URL);
            }else {
                LoseInternetConnMessage();
            }
        }catch (Exception e){
            SendOrderToTheThreadsToStopDownloading();
            Process process = java.lang.Runtime.getRuntime().exec("ping www.google.com");
            int x = process.waitFor();
            if(x == 0){
                UnValidUrlMessage(URL);
            }else {
                LoseInternetConnMessage();
            }
        }
    }

    //mesage that you lose your internet conn
    private void LoseInternetConnMessage(){
        helper.LoseInternetConnMessage();
        spider.setStartDownloadAgain(true);
    }

    // message that the url is not valid
    private void UnValidUrlMessage(String message){
        helper.CancelDownloadingFromPauseGui();
        th4SD.StopDownloading();
        helper.UnValidUrlMessage(message);
    }

    // restart Downloading
    void StartDownloadingAgain(){
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
    public void SendOrderToTheThreadsToStopDownloading(){
        th4SD.StopDownloading();
    }

    }


