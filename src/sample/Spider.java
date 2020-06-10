package sample;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Spider implements methods{
    private Connection connection = new Connection();
    private pauseGui pause = new pauseGui();
    //private PageFile file;
    private List<String> urlsList;
    private String SaveLocation;
    private String URL;
    volatile private Boolean repeat;


    public void setSaveLocation(String saveLocation) {
        SaveLocation = saveLocation;
        //file.setLocation;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }


    //Run method to start the operation
    public void Start() throws IOException, InterruptedException {

        //filterUrls();
        //saveUrls();
        RunThreads();
    }

    //run threads
    public void RunThreads(){
        threads[] threadsArray = new threads[5];
        repeat = false;
        for(threads th : threadsArray){
            try{
                th = new threads();
                th.start();
                th.join();

            }catch (InterruptedException e){
                System.out.print("interrupt exception");
            }
        }
    }
    //send basic url to filter and get all urls
    private void filterUrls() throws IOException {
        urlsList = new ArrayList<String>();
        urlsList = filter.getAllLink(URL);
    }

    //send urlsList to urlFile to save it in txt file
    private void saveUrls(){
        //file.saveUrlsInTxtFile(urlsList);
    }

    //------------------------innerClass--------------------------\\

    //inner threads class for make it faster
private class threads extends Thread {

        //override run method for threads
        @Override
        public void start(){

                //if there is still urls in downloading txt file
                if(/*file.thereIsStillUrlsInDownloading()*/ true) {

                    //String stackUrl = file.getStackUrl();
                    try {

                        Document Page = connection.connect(/*oneUrl*/"http://www.guimp.com/");
                        System.out.print(Page);
                    } catch (IOException e) {

                        cancelThread();
                        if(!repeat){
                            repeat = true;
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            // call firstKid fun
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            pause.start(new Stage());
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                });

                        }
                    }
                }

                //SendPage(page);
                //deleteOneUrl(oneUrl);

            /*
            while( true && !interrupted()){

                //String oneUrl = file.getOneUrl();
                try {

                    Document Page = connection.connect("http://www.guimp.com/");
                    System.out.print(Page);
                } catch (IOException e) {
                    e.printStackTrace();

                    cancelThread();
                    if(!repeat){
                        repeat = true;
                        try {
                            sleep(5000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        // call firstKid fun
                        try {
                            pause.start(new Stage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                //SendPage(page);
                //deleteOneUrl(oneUrl);
            }
*/


        }

        //cancel thread
        private void cancelThread(){
            interrupt();
        }
        //send page to PageFile class
        private void SendPage(String page){
        /*
        file.Write(page);
        */
        }
    }
}
