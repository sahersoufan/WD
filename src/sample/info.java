package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class info extends Application implements Objects4GUI{

    Parent root;


    @Override
    public void start(Stage primaryStage) throws Exception{

        root = FXMLLoader.load(getClass().getResource("info.fxml"));
        primaryStage.setTitle("Information");
        primaryStage.setScene(new Scene(root, 550, 124));
        primaryStage.show();
    }




    // Set full size of the website
    @FXML
    public void set(String FullSize)throws Exception{

        Label fullsize = (Label)root.lookup("#fullsize");
        fullsize.setText(FullSize);

    }

    // update the progress bar and the downloading value
    @FXML
    public void updateDownloadingState(String newUpdate){
        Label downloadingsize = (Label)root.lookup("#Downloadingsize");
        downloadingsize.setText(newUpdate);
    }

    //set origin path folder
    @FXML
    public void setOriginPathFolder(String OriginPath){
        Label path = (Label)root.lookup("#pathfolder");

        path.setText(OriginPath);
    }

    //close
    @FXML Button cancelBtn;
    @FXML Button pauseBtn;
    @FXML Button resumeBtn;
    @FXML Button fileLocation;
    @FXML
    public void Cancel() throws IOException {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        helper.CancelDownloading();
    }

    //pause
    @FXML
    public void Pause() throws IOException {
        resumeBtn.setDisable(false);
        pauseBtn.setDisable(true);
        helper.PauseDownloading();
    }
    //resume
    @FXML
    public void Resume() throws IOException {
        pauseBtn.setDisable(false);
        helper.ResumeDownloading();
    }

    //fileLocation
    @FXML
    public void OpenFileLocation(){
        helper.OpenFileLocation();
    }

    // close information Gui from pause command
    public  void CancelDownloadingFromPauseGui(){
        Button close = (Button) root.lookup("#cancelBtn");
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }


    //Progress bar
    public void startProgress(){

        Label fullsize = (Label)root.lookup("#fullsize");
        Label downloadingsize = (Label)root.lookup("#Downloadingsize");
        ProgressBar progressBar =(ProgressBar) root.lookup("#progressBar");

        long FZ = Long.parseLong(fullsize.getText());
        Task<Void> task = new Task<Void>() {
            long DZ;
            @Override
            protected Void call() throws Exception {

                while((DZ = helper.getDownloadingSize()) <= FZ){
                    updateProgress(DZ,FZ);
                    updateMessage(Long.toString(DZ));
                    Thread.sleep(10);
                }
                return null;
            }
        };
            task.messageProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    downloadingsize.setText(t1);
                }
            });

        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(task.progressProperty());

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }


}
