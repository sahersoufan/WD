package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class info extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
        primaryStage.setTitle("information");
        primaryStage.setScene(new Scene(root, 550, 124));
        primaryStage.show();
    }



    //@FXML private Button bu;
    @FXML
    public void set() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
        Label file = (Label) root.lookup("#file");
        System.out.print(file.getText());
        //file.setText("hello");
    }

    //put full size
    //update
    //close
    //pause
    //fileLocation

}
