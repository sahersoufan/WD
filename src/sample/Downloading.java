package sample;


import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Downloading {
    private info information = new info();


    public void RunInformationGui() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    information.start(new Stage());
                    //information.set(Long.toString(getFullSize()));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
