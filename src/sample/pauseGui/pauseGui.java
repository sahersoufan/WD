package sample.pauseGui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Objects4GUI;
import sample.threads.thread4StartPauseGui;
import java.io.IOException;

public class pauseGui extends Application implements Objects4GUI {

    @Override
    public void start(Stage pauseStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pauseGui.fxml"));
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.setResizable(false);

        pauseStage.setTitle("Pause Download");
        pauseStage.setScene(new Scene(root, 500, 100));
        pauseStage.show();
    }

    @FXML private javafx.scene.control.Button resumeid;
    @FXML
    public void Resume() throws IOException, InterruptedException {
        Stage stage = (Stage) resumeid.getScene().getWindow();
        stage.close();
        thread4StartPauseGui th = new thread4StartPauseGui();
        th.setHelper(helper);
        new Thread(th).start();
    }

    @FXML private javafx.scene.control.Button cancelid;
    @FXML
    public void Cancel(){
        helper.CancelDownloadingFromPauseGui();
        Stage stage = (Stage) cancelid.getScene().getWindow();
        stage.close();
    }
}