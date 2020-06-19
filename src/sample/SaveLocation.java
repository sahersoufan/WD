package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveLocation{


    public String ChoisLocation(){

        String location;
        Stage windo=new Stage();
        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialFileName("website");
        fileChooser.setInitialDirectory(new File("C:\\"));
        File file=  fileChooser.showSaveDialog(windo);

        if(file==null)
            location="C:\\";
        else {
            location = file.getPath();

        }

        return location;
    }
}