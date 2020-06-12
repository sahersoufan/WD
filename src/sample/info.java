package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class info extends Application{

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
    public void updateDownloadingState(){
        Label fullsize = (Label)root.lookup("#fullsize");
        fullsize.setText("FullSize");
    }
    //update

    //close

    //pause

    //fileLocation

}
