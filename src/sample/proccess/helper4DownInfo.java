package sample.proccess;

import javafx.application.Platform;
import javafx.stage.Stage;
import sample.CheckConn.CheckYourInternet;
import sample.informationGui.endGui;
import sample.informationGui.info;
import sample.pauseGui.pauseGui;
import sample.unvalidUrl.unvalidUrl;

import java.io.IOException;

public class helper4DownInfo {
    private Downloading download;
    private sample.pauseGui.pauseGui pauseGui = new pauseGui();
    private info information = new info();
    private endGui EndGui = new endGui();
    private unvalidUrl unvalidUrl = new unvalidUrl();
    private CheckYourInternet  checkConn = new CheckYourInternet();
    public void setD(Downloading d) {
        this.download = d;
    }

    //-----------------------informationGUI-------------------------\\

    //Run information GUI
    public void RunInformationGUI() throws Exception {
        Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                      try {
                          information.start(new Stage());
                      } catch (Exception ex) {
                          ex.printStackTrace();
                      }
                  }
              });
    }

    // Update download size label
    public void setUpdateDownloadingSizeLabel(String updateSize){

        information.updateDownloadingState(updateSize);

    }

    //set FUll size text Label
    public void setFullSizeLabel(String fullSize) throws Exception {
        information.set(fullSize);
    }

    //set title of info gui
    public void setTitle4InfoGui(String mainTitle) throws Exception {
        information.setTitle4InfoGui(mainTitle);
    }

    //set origin path of downloading folder
    public void setOriginPathFolder(String originPath) throws InterruptedException {

        information.setOriginPathFolder(originPath);

    }

    // send an order to Downloading to stop the download operation
    public  void CancelDownloading(){
        download.CancelDownloading();
    }

    // send an order to Downloading to stop the download operation
    public  void CancelDownloadingFromPauseGui(){
        information.CancelDownloadingFromPauseGui();
    }

    // send order to downloading to pause the download operation
    public void PauseDownloading(){
        download.PauseDownloading();
    }

    // send order to downloading to resume the download operation
    public void ResumeDownloading() throws IOException, InterruptedException {
        download.ResumeDownloading();
    }

    //send order to downloading to open file location that we download web site in it
    public void OpenFileLocation() throws IOException {
        download.OpenFileLocation();
    }

    //get downloading size
    public long getDownloadingSize() throws IOException{
        return download.getDownloadingSize();
    }

    public void update(){
        information.startProgress();
    }



    //-----------------------pauseGUI-------------------------\\
    public void RunPauseGui() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    pauseGui.start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    //-----------------------EndGUI-------------------------\\

    public void RunEndGui(String name) throws Exception{
        EndGui.start(new Stage());
        EndGui.SetNameWebSIte(name);
    }

    //-----------------------unValidUrlGui-------------------------\\


    // message that the url is not valid
    public void UnValidUrlMessage(String message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    unvalidUrl.start(new Stage());
                    unvalidUrl.SetUrlMessage(message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // continue download other urls
    public void StartDownloadingAgain(){
        download.StartDownloadingAgain();
    }
    //-----------------------LoseInternetConnInGetLinkGui-------------------------\\

    //mesage that you lose your internet conn
    public void LoseInternetConnMessage(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    checkConn.start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //ok i check please restart the spider
    public void restartTheSpiders() throws Exception {
        download.StartSpiderWorking();
    }
}
