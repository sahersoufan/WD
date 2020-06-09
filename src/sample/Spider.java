package sample;

import java.util.ArrayList;
import java.util.List;

public class Spider{
    private Connection connection;
    //private PageFile file;
    private List<String> urlsList;
    private String SaveLocation;
    private String URL;


    public void setSaveLocation(String saveLocation) {
        SaveLocation = saveLocation;
        //file.setLocation;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }


    //Run method to start the operation
    public void Start() throws InterruptedException {

        filterUrls();
        saveUrls();
        threads[] threadsArray = new threads[5];
        for(threads th : threadsArray){
            th.run();
            th.join();
        }
    }


    //send basic url to filter and get all urls
    private void filterUrls(){
        urlsList = new ArrayList<String>();
        //urlsList = filterIt(URL);
    }

    //send urlsList to urlFile to save it in txt file
    private void saveUrls(){
        //file.saveUrlsInTxtFile(urlsList);
    }

    //------------------------innerClass--------------------------\\

    //inner threads class for make it faster
private class threads extends Thread{

        //override run method for threads
        @Override
        public void run(){
            while(/*file.ThereIsStillURLS()*/ true){
                //String oneUrl = file.getOneUrl();
                //Document Page = connection.connect(oneUrl);
                //SendPage(page);
            }

        }

        //send page to PageFile class
        private void SendPage(String page){
        /*
        file.Write(page);
        */
        }
    }
}
