package sample.proccess;

import org.jsoup.nodes.Document;
import sample.getUrlFiles.Connection;
import sample.Paths.PageFiles;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Spider {
    private Downloading download;
    private Connection connection = new Connection();
    private String SaveLocation;
    private String URL;
    private List<String> urlsList = new ArrayList<String>();
    private List<String> Types;
    private int depth;
    private ALLURL allUrl = new ALLURL();
    private PageFiles file;
    private boolean errorInGetAllLink = false;
    private boolean StartDownloadAgain = false;
    volatile private Boolean repeat = false;
    volatile private Boolean repeatStopDownloading = false;
    volatile private Boolean CancelStatementInfoGui = false;
    volatile private Boolean PauseStatementInfoGui = false;


    public void setDownload(Downloading download) {
        this.download = download;
    }

    public void setSaveLocation(String saveLocation) {
        SaveLocation = saveLocation;
    }

    public void setTypes(List<String> t){Types = new ArrayList<>(t);}

    public void setDepth(int d){depth = d;}

    public void initPageFile() throws IOException {
        file = new PageFiles(SaveLocation);
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setStartDownloadAgain(boolean startDownloadAgain) {
        StartDownloadAgain = startDownloadAgain;
    }

    //Run method to start the operation
    public void InitSpiderProp() throws Exception {
        filterUrls();
        saveUrls();
        setFullSizeLabel();
        setOriginPathFolder();
        setTitle4InfoGui();
    }

    //Run threads
    public void StartSpiderThreads() throws IOException, InterruptedException {
        RunThreads();
    }

    //run threads
    public void RunThreads() throws IOException, InterruptedException {
        threads[] threadsArray = new threads[5];
        repeat = false;
        repeatStopDownloading = false;
        CancelStatementInfoGui = false;
        PauseStatementInfoGui = false;
        for (int i = 0; i < threadsArray.length; i++) {
            threadsArray[i] = new threads();
            threadsArray[i].start();
        }
        for (int i = 0; i < threadsArray.length; i++) {
            threadsArray[i].join();
        }
        if (file.isURL_InURL_Text()) {
            file.deleteURLs();
            file.deleteDownloading();
            file.repair(URL);
            if (StartDownloadAgain) {
                StartDownloadAgain = false;
                download.StartDownloadingAgain();
            }
        }
    }

    //set full size of web site
    public void setFullSizeLabel() throws Exception {
        download.setFullSizeLabel(getFullSize() + " file");
    }

    //set update for downloading size
    public void setUpdateDownloadingSizeLabel() throws Exception {
        long downloadingSize = 0;
        download.setUpdateDownloadingSizeLabel(downloadingSize + " mb");
    }

    //set origin path folder
    public void setOriginPathFolder() throws Exception {
        download.setOriginPathFolder(SaveLocation);
    }

    //set title of info gui
    public void setTitle4InfoGui() throws Exception {
        download.setTitle4InfoGui(urlsList.get(0));
    }

    //Get Full Size of Web Site
    private long getFullSize() {
        return urlsList.size();
    }

    //get downloading size
    public long getDownloadingSize() throws IOException {
        return file.numberOfLinesIn_URLS();

    }


    //send basic url to filter and get all urls
    private void filterUrls() throws IOException {
        urlsList = allUrl.getAllLink(URL, Types,depth);
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

    // send orders to download to stop for loop
    public void SendOrderToDownloadToStopDownloading() {
        download.SendOrderToTheThreadsToStopDownloading();
    }

    //------------------------innerClass--------------------------\\

    //inner threads class for make it faster
    private class threads extends Thread {

        //send page to PageFile class
        private void SendPage(String page,BufferedImage img,InputStream audio,InputStream video, String url) throws IOException {
            file.saveIn(page, img,audio,video ,url);
        }

        //delete one url from urls.txt
        private void deleteOneUrlFromDownloading(String url) throws IOException {
            file.removeOneURL_FromDownloading(url);
        }

        //override run method for threads
        @Override
        public void start() {

            BufferedImage image = null;
            String webPage;
            String oneUrl = "hello";

            //if there is still urls in downloading txt file
            if (!file.isURL_InDownloading()) {

                try {
                    oneUrl = file.getOneURL_From_Downloading();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if((Boolean) Filter.FilterVideo(oneUrl)){
                        InputStream i = connection.downloadVideo(oneUrl);
                        try {
                            SendPage("",null,null,i, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if((Boolean) Filter.FilterAduio(oneUrl)){
                        InputStream i = connection.downloadAudio(oneUrl);
                        try {
                            SendPage("",null,i,null, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if((Boolean) Filter.FilterImage(oneUrl)){
                        image = connection.DownloadImage(oneUrl);
                        try {
                            SendPage("",image,null,null, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        webPage = connection.DownloadWebPage(oneUrl);
                        try {
                            SendPage(webPage,null,null,null, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        deleteOneUrlFromDownloading(oneUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    if (!e.toString().contains("Unhandled content type.") && !e.toString().contains("code: 400") && !e.toString().contains("FileNotFoundException")) {

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
                    if((Boolean) Filter.FilterVideo(oneUrl)){
                        InputStream i = connection.downloadVideo(oneUrl);
                        try {
                            SendPage("",null,null,i, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if((Boolean) Filter.FilterAduio(oneUrl)){
                        InputStream i = connection.downloadAudio(oneUrl);
                        try {
                            SendPage("",null,i,null, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if((Boolean) Filter.FilterImage(oneUrl)){
                        image = connection.DownloadImage(oneUrl);
                        try {
                            SendPage("",image,null,null, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        webPage = connection.DownloadWebPage(oneUrl);
                        try {
                            SendPage(webPage,null,null,null, oneUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    try {
                        deleteOneUrlFromDownloading(oneUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                        if (!e.toString().contains("Unhandled content type.") && !e.toString().contains("code: 400")&&!e.toString().contains("code: 403") && !e.toString().contains("FileNotFoundException")) {

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
                if (PauseStatementInfoGui && !repeatStopDownloading) {
                    repeatStopDownloading = true;
                    StartDownloadAgain = true;
                    SendOrderToDownloadToStopDownloading();
                }

            }


        }

    }
}