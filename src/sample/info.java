package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class info extends Application  implements methods{

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
    //update

    //set origin path folder
    @FXML
    public void setOriginPathFolder(String OriginPath){
        Label path = (Label)root.lookup("#pathfolder");
        path.setText(OriginPath);
    }
    //Path

    //close downloading
    @FXML Button cancelBtn;
    @FXML
    public void Cancel() throws IOException {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        pr();

    }
    public void pr(){
        d.print();
    }

    @FXML private void initialize(){
    }

    //close

    //pause

    //fileLocation

}
