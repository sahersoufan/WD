package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
<<<<<<< HEAD

      //  launch(args);
        ALLURL.getAllLink("http://www.guimp.com/");
=======
        launch(args);
>>>>>>> 1f1306d924196e57ed5d8005e8e6f48d8236a4f5
    }
}
