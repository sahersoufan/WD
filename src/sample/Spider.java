package sample;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.jsoup.nodes.Document;
import sample.Paths.PageFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spider{
    private Downloading download;
    private Connection connection = new Connection();
    private pauseGui pause = new pauseGui();
    private String SaveLocation;
    private String URL;
    private List<String> urlsList;
    volatile private Boolean repeat;
    volatile private long downloadingSize = 0;
    private ALLURL allUrl = new ALLURL();
    private PageFiles file;
    volatile private Boolean CancelStatementInfoGui = false;


    public void setDownload(Downloading download) {
        this.download = download;
    }

    public void setSaveLocation(String saveLocation) {
        SaveLocation = saveLocation;
        //file.setLocation;
    }
    public void setURL(String URL) {
        this.URL = URL;
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
        download.setFullSizeLabel(Long.toString(getFullSize()));
    }

    //set update for downloading size
    public void setUpdateDownloadingSizeLabel() throws Exception {
        download.setUpdateDownloadingSizeLabel(Long.toString(downloadingSize));
    }

    //set origin path folder
    public void setOriginPathFolder() throws Exception {
        download.setOriginPathFolder(SaveLocation);
    }

    //Get Full Size of Web Site
    private long getFullSize(){
        return /*filter.getFullSize();*/ 500;
    }

    //run threads
    public void RunThreads(){
        threads[] threadsArray = new threads[5];
        repeat = false;
        CancelStatementInfoGui = false;
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

    //Cancel threads work
    public void setCancelStatementInfoGui(Boolean cancelStatementInfoGui) {
        CancelStatementInfoGui = cancelStatementInfoGui;
    }

    // pause the download operation
    public void PauseDownloading(){
        CancelStatementInfoGui = true;
    }

    // resume the download operation
    public void ResumeDownloading(){
        RunThreads();
    }


    //------------------------innerClass--------------------------\\

    //inner threads class for make it faster
private class threads extends Thread {

        //override run method for threads
        @Override
        public void start(){

                //if there is still urls in downloading txt file
            try {
                if(file.isURL_InDownloading()) {

                    String stackUrl = file.getOneURL_From_Downloading();
                    try {

                        Document Page = connection.connect(stackUrl);
                        System.out.print(Page);
                    } catch (IOException e) {

                        if(!repeat){
                            repeat = true;
                            try {
                                sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            download.RunPauseGui();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //SendPage(page);
            //deleteOneUrl(oneUrl);

            /*
            while( true && !interrupted() && !CancelStatementInfoGui){

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
       /* private void cancelThread(){
            interrupt();
        }
        //send page to PageFile class
        private void SendPage(String page){
        /*
        file.Write(page);
        */
        }
    }

