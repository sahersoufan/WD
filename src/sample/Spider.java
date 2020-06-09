package sample;

public class Spider {
    //private HTTPConnection connection;
    //private PageFile file;
    private String SaveLocation;
    private String URL;

    //search method to find the page and get it //
    public void Search(){
        /*
        HTTPConnection.SetURL(URL);
        String page = HTTPConnection.connection();
        this.SendPage(page);
         */
    }
    //send page to PageFile class
    private void SendPage(String page){
        /*
        file.Write(page);
         */
    }

    public void setSaveLocation(String saveLocation) {
        SaveLocation = saveLocation;
        //file.setLocation;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
