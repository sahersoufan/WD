package sample.informationGui;

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

public class endGui extends Application {
    Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("endGui.fxml"));
        stage.setTitle("End Download");
        stage.setScene(new Scene(root, 300 , 120));
        stage.show();
    }

    @FXML private Button CloseBtn;
    @FXML
    private void CLoseIt(){
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }

    public void SetNameWebSIte(String name){
        Label webSiteName = (Label) root.lookup("#webSiteName");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    webSiteName.setText(name);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
