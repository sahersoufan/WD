package sample.CheckConn;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexedCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Objects4GUI;
import sample.threads.thread4StartSpiderAgain;

import java.awt.*;

public class CheckYourInternet extends Application implements Objects4GUI {
    Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("CheckYourInternet.fxml"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Check Your Internet");
        stage.setScene(new Scene(root, 300 , 120));
        stage.show();
    }

    @FXML private Button CancelBtn;
    @FXML private Button ICheckIt;

    @FXML
    private void cancel(){
        helper.CancelDownloadingFromPauseGui();
        Stage s = (Stage) CancelBtn.getScene().getWindow();
        s.close();
    }

    @FXML
    private void ICheckIt() throws Exception {
        helper.CancelDownloadingFromPauseGui();
        Stage s = (Stage) ICheckIt.getScene().getWindow();
        s.close();
        thread4StartSpiderAgain th = new thread4StartSpiderAgain();
        th.setHelper(helper);
        new Thread(th).start();

    }


}
