package sample;


import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Downloading {
    private info information = new info();
    private Spider spider = new Spider();
    private betweeninfodownloader between ;
    private String URL="aa", saveLocation="D:\\NEW HORIZON\\WEB";


    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    //set text Label
    public void setFullSizeLabel(String fullSize){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    information.set(fullSize);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    public void setUpdateDownloadingSizeLabel(String updateSize){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    information.updateDownloadingState(updateSize);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void setOriginPathFolder(String originPath){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    information.setOriginPathFolder(originPath);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //Start method
    public void Start() throws Exception {

        spider.setURL(URL);
        spider.setSaveLocation(saveLocation);
        RunInformationGui();
        spider.FirstStep();
        //spider.setFullSizeLabel();
        //spider.secondStep();
    }



    public  void print(){
        System.out.print("close");
    }
    public void RunInformationGui() throws Exception {
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
}
