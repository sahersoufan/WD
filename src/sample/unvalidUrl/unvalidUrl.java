package sample.unvalidUrl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Objects4GUI;

public class unvalidUrl extends Application implements Objects4GUI {

    private Parent root;

    @Override
    public void start(Stage stage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("unvalidUrl.fxml"));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Unvalid Url");
        stage.setScene(new Scene(root, 300 , 120));
        stage.show();
    }

    @FXML private Button CloseBtn;
    @FXML
    private void CLoseIt(){
        helper.StartDownloadingAgain();
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }


    public void SetUrlMessage(String message){
        Label Urlname = (Label) root.lookup("#Urlname");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Urlname.setText(message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


}
