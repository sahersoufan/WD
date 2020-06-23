package sample.process;

import org.jsoup.nodes.Document;
import sample.getUrlFiles.Connection;
import sample.Paths.PageFiles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Spider {
    private Downloading download;
    private Connection connection = new Connection();
    private String SaveLocation;
    private String URL;
    private List<String> urlsList = new ArrayList<String>();;
    private long downloadingSize = 0;
    private ALLURL allUrl = new ALLURL();
    private PageFiles file;
    volatile private Boolean repeat = false;
    volatile private Boolean CancelStatementInfoGui = false;
    volatile private Boolean PauseStatementInfoGui = false;




    public void setDownload(Downloading download) {
        this.download = download;
    }

    public void setSaveLocation(String saveLocation) {
        SaveLocation = saveLocation;
    }

    public void initPageFile() throws IOException {
        file = new PageFiles(SaveLocation);
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    //Run method to start the operation
    public void FirstStep() throws Exception {
        filterUrls();
        saveUrls();
        setFullSizeLabel();
        setOriginPathFolder();
    }

    //Run threads
    public void secondStep() throws IOException, InterruptedException {
        RunThreads();
    }

    //set full size of web site
    public void setFullSizeLabel() throws Exception {
        download.setFullSizeLabel(getFullSize() +" file");
    }

    //set update for downloading size
    public void setUpdateDownloadingSizeLabel() throws Exception {
        download.setUpdateDownloadingSizeLabel(downloadingSize +" mb");
    }

    //set origin path folder
    public void setOriginPathFolder() throws Exception {
        download.setOriginPathFolder(SaveLocation);
    }

    //Get Full Size of Web Site
    private long getFullSize() {
        return urlsList.size();
    }

    //get downloading size
    public long getDownloadingSize() throws IOException {
        return file.numberOfLinesIn_URLS();

    }

    //run threads
    public void RunThreads() throws IOException, InterruptedException {
        threads[] threadsArray = new threads[5];
        repeat = false;
        CancelStatementInfoGui = false;
        PauseStatementInfoGui = false;
        for (int i = 0 ; i < threadsArray.length ; i++) {
            threadsArray[i] = new threads();
            threadsArray[i].start();
        }
        for (int i = 0 ; i < threadsArray.length ; i++) {
            threadsArray[i].join();
        }
        if(file.isURL_InURL_Text()){
            file.deleteURLs();
            file.deleteDownloading();
            file.repair(URL);
        }
    }

    //send basic url to filter and get all urls
    private void filterUrls() throws IOException {
        urlsList = allUrl.getAllLink(URL);
    }

    //send urlsList to urlFile to save it in txt file
    private void saveUrls() throws IOException {
        file.setURLS((ArrayList<String>) urlsList);
    }

    //Cancel threads work
    public void setCancelStatementInfoGui(Boolean cancelStatementInfoGui) {
        CancelStatementInfoGui = cancelStatementInfoGui;
    }

    // pause the download operation
    public void PauseDownloading() {
        PauseStatementInfoGui = true;
    }

    // resume the download operation
    public void ResumeDownloading() throws IOException, InterruptedException {
        RunThreads();
    }
    //open file location that we download web site in it
    public void OpenFileLocation() throws IOException {
        file.openInExplorer();
    }

    //------------------------innerClass--------------------------\\

    //inner threads class for make it faster
    private class threads extends Thread {

        //override run method for threads
        @Override
        public void start() {

            Document Page = new Document("hi");
            String webPage ;
            String oneUrl = "hello";

            //if there is still urls in downloading txt file
            if (!file.isURL_InDownloading()) {

                try {
                    oneUrl = file.getOneURL_From_Downloading();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    //Page = connection.connect(oneUrl);

                    webPage = connection.DownloadWebPage(oneUrl);
                    try {
                        SendPage(/*Page.toString()*/webPage,oneUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        deleteOneUrlFromDownloading(oneUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    if (!e.toString().contains("Unhandled content type.")) {
                        if (!repeat) {
                            PauseStatementInfoGui = true;
                            repeat = true;
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                download.RunPauseGui();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }



            }
            while (!CancelStatementInfoGui && !PauseStatementInfoGui && !file.isURL_InURL_Text()) {

                try {
                    oneUrl = file.getOneURL();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {

                    //Page = connection.connect(oneUrl);

                    webPage = connection.DownloadWebPage(oneUrl);

                    try {
                        SendPage(/*Page.toString()*/webPage,oneUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        deleteOneUrlFromDownloading(oneUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    if (!e.toString().contains("Unhandled content type.")) {
                        if (!repeat) {
                            PauseStatementInfoGui = true;
                            repeat = true;
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                download.RunPauseGui();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }

            }

        }

        //send page to PageFile class
        private void SendPage(String page, String url) throws IOException {
            file.saveIn(page , url);
        }

        //delete one url from urls.txt
        private void deleteOneUrlFromDownloading(String url) throws IOException {
            file.removeOneURL_FromDownloading(url);
        }
    }
}

