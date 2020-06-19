package sample;

import org.jsoup.nodes.Document;
import sample.Paths.PageFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spider {
    private Downloading download;
    private Connection connection = new Connection();
    private String SaveLocation;
    private String URL;
    private List<String> urlsList;
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
        setUpdateDownloadingSizeLabel();
        setOriginPathFolder();
    }

    //Run threads
    public void secondStep() {
        RunThreads();
    }

    //set full suze of web site
    public void setFullSizeLabel() throws Exception {
        download.setFullSizeLabel(getFullSize() +" mb");
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
        return allUrl.getSize();
    }

    //get downloading size
    public long getDownloadingSize(){
        //file.sizeOfFileInMB(/*repaire it mayar*/ "hello.txt");
        return downloadingSize ++;
    }

    //run threads
    public void RunThreads() {
        threads[] threadsArray = new threads[5];
        repeat = false;
        CancelStatementInfoGui = false;
        PauseStatementInfoGui = false;
        for (threads th : threadsArray) {
            try {
                th = new threads();
                th.start();
                th.join();

            } catch (InterruptedException e) {
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
    public void ResumeDownloading() {
        RunThreads();
    }


    //------------------------innerClass--------------------------\\

    //inner threads class for make it faster
    private class threads extends Thread {

        //override run method for threads
        @Override
        public void start() {

            Document Page = new Document("hi");
            String oneUrl = "hello";

            //if there is still urls in downloading txt file
            if (!file.isURL_InDownloading()) {

                try {
                    oneUrl = file.getOneURL_From_Downloading();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    Page = connection.connect(oneUrl);
                    if (PauseStatementInfoGui) {
                        throw new IOException();
                    }
                } catch (IOException e) {
                    if (!e.toString().contains("Unhandled content type.")) {
                        if (!repeat) {
                            repeat = true;
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            download.RunPauseGui();
                        }
                    }
                }


                try {
                    SendPage(Page.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    deleteOneUrlFromDownloading(oneUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            while (!CancelStatementInfoGui && !file.isURL_InURL_Text()) {

                try {
                    oneUrl = file.getOneURL();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (PauseStatementInfoGui) {
                        throw new IOException();
                    }

                    Page = connection.connect(oneUrl);


                } catch (IOException e) {
                    if (!e.toString().contains("Unhandled content type.")) {
                        if (!repeat) {
                            repeat = true;
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            download.RunPauseGui();
                        }
                    }
                }
                try {
                    SendPage(Page.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    deleteOneUrlFromDownloading(oneUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //send page to PageFile class
        private void SendPage(String page) throws IOException {
            file.saveIn(page);
        }

        //delete one url from urls.txt
        private void deleteOneUrlFromDownloading(String url) throws IOException {
            file.removeOneURL_FromDownloading(url);
        }
    }
}

