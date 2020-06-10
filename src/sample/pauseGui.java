package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class pauseGui extends Application {


    @Override
    public void start(Stage pauseStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pauseGui.fxml"));
        pauseStage.setTitle("Pause Download");
        pauseStage.setScene(new Scene(root, 500, 100));
        pauseStage.show();


    }

    @FXML private javafx.scene.control.Button resumeid;

    @FXML
    public void Resume() throws Exception {
        Spider spider = new Spider();
        Stage stage = (Stage) resumeid.getScene().getWindow();
        stage.close();

        spider.RunThreads();
    }

    @FXML private javafx.scene.control.Button cancelid;
    @FXML
    public void Cancel(){
        Stage stage = (Stage) resumeid.getScene().getWindow();
        stage.close();
    }
}
