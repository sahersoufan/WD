package sample;



public class Downloading implements Objects4GUI{
    private Spider spider = new Spider();
    private String URL="http://www.guimp.com/", saveLocation="D:\\";


    Downloading(){
        spider.setDownload(this);
        helper.setD(this);
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    //set FUll size text Label
    public void setFullSizeLabel(String fullSize){
        helper.setFullSizeLabel(fullSize);
    }

    // Update download size label
    public void setUpdateDownloadingSizeLabel(String updateSize){
            helper.setUpdateDownloadingSizeLabel(updateSize);
    }

    //set origin path of downloading folder
    public void setOriginPathFolder(String originPath){
        helper.setOriginPathFolder(originPath);
    }

    //Run information GUI
    public void RunInformationGui(){
        helper.RunInformationGUI();
    }

    //Start method
    public void Start() throws Exception {
        spider.setURL(URL);
        spider.setSaveLocation(saveLocation);
        //RunInformationGui();
        spider.FirstStep();
        //helper.update();
    }


    // send an order to spider to stop the download operation
    public  void CancelDownloading(){
        spider.setCancelStatementInfoGui(true);
    }

    // send order to spider to pause the download operation
    public void PauseDownloading(){
        spider.PauseDownloading();
    }

    // send order to spider to resume the download operation
    public void ResumeDownloading(){
        spider.ResumeDownloading();
    }

    //open file location that we download web site in it
    public void OpenFileLocation(){
        System.out.print("we will do it don't worry");
    }

    //get downloading size
    public long getDownloadingSize(){
        return spider.getDownloadingSize();
    }

    // Run Pause Gui
    public void RunPauseGui() {
        helper.RunPauseGui();
    }

    }


