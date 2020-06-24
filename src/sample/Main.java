package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sample.basicGui.BasicGui;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
    }


    public static void main(String[] args) throws Exception {
        BasicGui b = new BasicGui();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    b.start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        launch(args);

    }
}
