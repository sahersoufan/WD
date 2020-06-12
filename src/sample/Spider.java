package sample;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.jsoup.nodes.Document;
import sample.Paths.PageFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spider implements methods{
    private Connection connection = new Connection();
    private pauseGui pause = new pauseGui();
    private String SaveLocation;
    private String URL;
    private List<String> urlsList;
    volatile private Boolean repeat;
    volatile private long downloadingSize = 10;
    private ALLURL allUrl = new ALLURL();
    private PageFiles file;
    volatile private Boolean CancelStatementInfoGui = false;



    public void setSaveLocation(String saveLocation) {
        SaveLocation = saveLocation;
        //file.setLocation;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public void setCancelStatementInfoGui(Boolean cancelStatementInfoGui) {
        CancelStatementInfoGui = cancelStatementInfoGui;
    }

    //Run method to start the operation
    public void FirstStep() throws Exception {
        //filterUrls();
        //saveUrls();
        setFullSizeLabel();
        setUpdateDownloadingSizeLabel();
        setOriginPathFolder();
    }

    //Run threads
    public void secondStep(){
        RunThreads();
    }

    //set full suze of web site
    public void setFullSizeLabel() throws Exception {
        d.setFullSizeLabel(Long.toString(getFullSize()));
    }

    //set update for downloading size
    public void setUpdateDownloadingSizeLabel() throws Exception {
        d.setUpdateDownloadingSizeLabel(Long.toString(downloadingSize));
    }

    //set origin path folder
    public void setOriginPathFolder() throws Exception {
        d.setOriginPathFolder(SaveLocation);
    }
    public void print(){System.out.print("Asd");}









    //Get Full Size of Web Site
    private long getFullSize(){
        return /*filter.getFullSize();*/ 50;
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
        urlsList = allUrl.getAllLink(URL);
    }

    //send urlsList to urlFile to save it in txt file
    private void saveUrls() throws IOException {
        file = new PageFiles(SaveLocation);
        file.setURLS(urlsList,SaveLocation,URL);
    }



    //------------------------innerClass--------------------------\\

    //inner threads class for make it faster
private class threads extends Thread {

        //override run method for threads
        @Override
        public void start(){

                //if there is still urls in downloading txt file
            try {
                if(file.isURL_InDownloading() && !CancelStatementInfoGui) {

                    String stackUrl = file.getOneURL_From_Downloading();
                    try {

                        Document Page = connection.connect(stackUrl);
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
            } catch (IOException e) {
                e.printStackTrace();
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
