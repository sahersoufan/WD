package sample.process;

import javafx.application.Platform;
import javafx.stage.Stage;
import sample.informationGui.info;
import sample.pauseGui.pauseGui;

import java.io.IOException;

public class helper4DownInfo {
    private Downloading download;
    private sample.pauseGui.pauseGui pauseGui = new pauseGui();
    private info information = new info();
    private long i = 0;
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
    long j = 0;
    //get downloading size
    public long getDownloadingSize(){
        return /*download.getDownloadingSize()*/ j++;
    }

    public void update(){
        information.startProgress();
    }

    //check if information is running
    public boolean CheckRunInfo(){
        return information.CheckRunInfo();
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


}
