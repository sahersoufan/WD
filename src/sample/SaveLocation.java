package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveLocation extends Application {


    StackPane layout=new StackPane();

    @Override
    public void start(Stage pauseStage) throws Exception {


        Display();
        pauseStage.setTitle("Save Location");
        pauseStage.setScene(new Scene(layout, 500, 100));
        pauseStage.show();
    }

    void Display() {
        Button click=new Button("click me");
        click.setOnAction(e->{
            ChoisLocation();
        });
        layout.getChildren().addAll(click);
    }

    public String ChoisLocation(){

        String location;
        Stage windo=new Stage();
        FileChooser fileChooser=new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(" "),new FileChooser.ExtensionFilter(""));
        File file=  fileChooser.showSaveDialog(windo);

        if(file==null)
            location="C:\\";
        else
            location=  file.getPath();

        return location;
    }



}
