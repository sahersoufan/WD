package sample.informationGui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Objects4GUI;
import sample.threads.thread4ResumeDownloading;

import java.io.IOException;

public class info extends Application implements Objects4GUI {

    Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        root = FXMLLoader.load(getClass().getResource("info.fxml"));
        //primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Information");
        primaryStage.setScene(new Scene(root, 500, 130));
        primaryStage.showAndWait();
    }




    // Set full size of the website
    @FXML
    public void set(String FullSize)throws Exception{

        Label fullsize = (Label)root.lookup("#fullsize");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    fullsize.setText(FullSize);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    // update the progress bar and the downloading value
    @FXML
    public void updateDownloadingState(String newUpdate){
        Label downloadingsize = (Label)root.lookup("#Downloadingsize");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    downloadingsize.setText(newUpdate);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    //set origin path folder
    @FXML
    public void setOriginPathFolder(String OriginPath){
        Label path = (Label)root.lookup("#pathfolder");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    path.setText(OriginPath);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //set title of info gui
    public void setTitle4InfoGui(String mainTitle) throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage s = (Stage) root.getScene().getWindow();
                    s.setTitle(mainTitle);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });    }

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
    public void Resume() throws IOException, InterruptedException {
        pauseBtn.setDisable(false);
        thread4ResumeDownloading th = new thread4ResumeDownloading();
        th.setHelper(helper);
        new Thread(th).start();
        resumeBtn.setDisable(true);
    }

    //fileLocation
    @FXML
    public void OpenFileLocation() throws IOException {
        helper.OpenFileLocation();
    }

    // close information Gui from pause command
    public  void CancelDownloadingFromPauseGui(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Button close = (Button) root.lookup("#cancelBtn");
                    Stage stage = (Stage)close.getScene().getWindow();
                    stage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }



    //Progress bar
    public void startProgress(){

        Label fullsize = (Label)root.lookup("#fullsize");
        Label downloadingsize = (Label)root.lookup("#Downloadingsize");
        ProgressBar progressBar =(ProgressBar) root.lookup("#progressBar");

        String StringFZ = fullsize.getText();
        String[] FZStringSplit = StringFZ.split(" ");
        long FZ = Long.parseLong(FZStringSplit[0]);

        Task<Void> task = new Task<Void>() {
            long DZ;
            @Override
            protected Void call() throws Exception {

                while((DZ = FZ - helper.getDownloadingSize()) <= FZ){
                    updateProgress(DZ,FZ);
                    updateMessage(DZ + "");
                    Thread.sleep(100);
                    if(DZ == FZ)
                        break;
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            // <------- Start End Gui ------->

                            Stage stage = (Stage) root.getScene().getWindow();
                            helper.RunEndGui(stage.getTitle());
                            stage.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });


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
