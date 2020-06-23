package sample.Paths;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.process.ALLURL;
import sample.process.Filter;
import sample.process.Repair;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PageFiles{
    private static final String DOWNLOADING_FILE="downloading.txt";
    private static final String URLs_FILE="URLs.txt";
    private String mainPath;
    private Path CSS;
    private Path HTML;
    private Path media;
    private Path JS;
    ALLURL allurl;
    public PageFiles(String mainPath) {
        this.mainPath=mainPath;
        CSS=new CSS(mainPath,"CSS");
        HTML=new HTML(mainPath,"HTML");
        JS=new JS(mainPath,"JS");
        media=new Media(mainPath,"Media");
    }
    public synchronized void setURLS(ArrayList<String> urls) throws IOException {
        File folder=new File(getMainPath());
        folder.mkdirs();
        String pathOfURLS=getMainPath()+File.separator+URLs_FILE;
        File saveURLS=new File(pathOfURLS);
        if (!(saveURLS.exists())){
            boolean safe= saveURLS.createNewFile();
            if (safe)
                System.out.println("Created successful "+pathOfURLS);
            else
                System.err.println("unsafe new file");
        }
        String pathOfDownloading=getMainPath()+File.separator+DOWNLOADING_FILE;
        File saveDownloading=new File(pathOfDownloading);
        if (!(saveDownloading.exists())){
            boolean safe= saveDownloading.createNewFile();
            if (safe)
                System.out.println("Created successful "+pathOfDownloading);
            else
                System.err.println("unsafe new file");
        }

        for (String temp:urls){
            getHTML().writeFile(pathOfURLS,temp);
        }

        //issue
       // deleteFirstLine(pathOfURLS);
    }
    public synchronized void saveIn(String data,String url) throws IOException {
        if((Boolean) Filter.FilterCss(url))

            getCSS().writeFile(setCSS_File(url),data);

        else if((Boolean) Filter.FilterJs(url))

            getJS().writeFile(setJS_File(url),data);

        else if((Boolean) Filter.FilterImage(url))

            getMedia().writeFile(setMedia_File(url),data);

        else if((Boolean) Filter.FilterAduio(url))

            getMedia().writeFile(setAudio_File(url),data);
        else
            getHTML().writeFile(setHTML_File(url),data);
    }
    public synchronized void deleteFirstLine(String file) throws IOException {
        File path = new File(file);
        Scanner scanner = new Scanner(path);
        ArrayList<String> coll = new ArrayList<String>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            coll.add(line);
        }

        scanner.close();

        FileWriter writer = new FileWriter(path);
        for (String line : coll) {
            writer.write(line+"\n");
        }

        writer.close();
    }
    public synchronized String getOneURL() throws IOException {
        String oneLine=getHTML().readOneLine(getMainPath()+File.separator+URLs_FILE);
        getHTML().writeOneLine(getMainPath()+File.separator+DOWNLOADING_FILE,oneLine);
        deleteFirstLine(getMainPath()+File.separator+URLs_FILE);
        return oneLine;
    }
    public synchronized String getOneURL_From_Downloading() throws IOException {

        return getHTML().readOneLine(getMainPath()+File.separator+DOWNLOADING_FILE);
    }
    public synchronized void  removeOneURL_FromDownloading(String url) throws IOException {
        File inputFile = new File(getMainPath()+File.separator+DOWNLOADING_FILE);
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(url)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        System.out.println(successful);
        inputFile.delete();
        tempFile.renameTo(new File(getMainPath()+File.separator+DOWNLOADING_FILE));

    }
    public synchronized void  removeOneURL_FromURLs() throws IOException {
        File path = new File(getMainPath()+File.separator+URLs_FILE);
        Scanner scanner = new Scanner(path);
        ArrayList<String> coll = new ArrayList<String>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            coll.add(line);
        }

        scanner.close();

        FileWriter writer = new FileWriter(path);
        for (String line : coll) {
            writer.write(line+"\n");
        }

        writer.close();
    }
    public synchronized boolean  isURL_InDownloading() {
        File file = new File(getMainPath()+File.separator+DOWNLOADING_FILE);
        return !file.exists() || file.length() <= 0;
    }
    public synchronized boolean isURL_InURL_Text(){
        File file=new File(getMainPath()+File.separator+URLs_FILE);
        return !file.exists() || file.length() <= 0;
    }
    public long sizeOfFileInKB(){
        File f = new File(mainPath);
        return f.length()/1024;
    }
    public long sizeOfFileInMB(){
        return sizeOfFileInKB()/1024;
    }
    public void openInBrowser(){
        try {
            File htmlFile = new File(getMainPath()+File.separator+"index.html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (Exception e){
            System.err.println("error to open browser because you insert false path of file that you wont to open");
            System.err.println(e.getMessage());
        }
    }
    public void openInExplorer() throws IOException {
        File file = new File (getMainPath()+File.separator);
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
    public void deleteURLs(){
        try
        {
            File f= new File(getMainPath()+File.separator+URLs_FILE);
            if(f.delete())
            {
                System.out.println(f.getName() + " deleted");
            }
            else
            {
                System.out.println("failed");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void deleteDownloading(){
        try
        {
            File f= new File(getMainPath()+File.separator+DOWNLOADING_FILE);
            if(f.delete())
            {
                System.out.println(f.getName() + " deleted");
            }
            else
            {
                System.out.println("failed");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void repair(String mainUrl) throws IOException {
        String path=getMainPath()+File.separator+"HTML";
        String[] pathNames;
        File f=new File(path);
        pathNames=f.list();
        String copy;
        ArrayList<String> allLinks;
        ArrayList<String> allLinksPaste;
        assert pathNames != null;
        for (String pathName:pathNames){
            copy=getHTML().readFile(path+File.separator+pathName);
            allLinks=getAllSubLinks(copy,mainUrl);
            allLinksPaste=getAllSubLinks(copy,mainUrl);
            for (int i=0;i<allLinksPaste.size();i++){
                String link=allLinksPaste.get(i);
                String[] parts = link.split("/");
                String type=Filter.filterType(link);
                File subFile=new File(getMainPath()+File.separator+type);
                String[] subPathNames=subFile.list();
                String paste;
                assert subPathNames != null;
                for (String sub:subPathNames){
                    if (sub.equals(link)){
                        paste=getMainPath()+File.separator+type+File.separator+parts[parts.length-1];
                        allLinksPaste.set(i,paste);
                    }
                }
            }
            for (int i=0;i<allLinks.size();i++){
                copy=copy.replace(allLinks.get(i),allLinksPaste.get(i));
            }
            File fDelete=new File(path+File.separator+pathName);
            fDelete.delete();
            File fNew=new File(path+File.separator+pathName);
            getHTML().writeFile(path+File.separator+pathName,copy);
        }
    }
    public  ArrayList<String> getAllSubLinks(String html, String MainUrl){
        ArrayList<String>allLinks=new ArrayList<>();
        Document doc= Jsoup.parse(html);
        Elements links=doc.select("a[href]");
        Elements links2 = doc.select("link[href]");
        for (Element link :links){
            Repair.RepairUrl(MainUrl,link.attr("href"));
            allLinks.add( Repair.RepairUrl(MainUrl,link.attr("href")));
        }
        for (Element link :links2){
            Repair.RepairUrl(MainUrl,link.attr("href"));
            allLinks.add( Repair.RepairUrl(MainUrl,link.attr("href")));
        }
        return allLinks;
    }
    public int numberOfLinesIn_URLS() throws IOException {
        return getHTML().numberOfLines(getMainPath()+File.separator+URLs_FILE);
    }
    private String getMainPath() {
        return mainPath;
    }
    private Path getHTML() {
        return HTML;
    }
    private Path getMedia() {
        return media;
    }
    private Path getJS() {
        return JS;
    }
    private Path getCSS() {
        return CSS;
    }
    private String setHTML_File(String url){
        String[] parts=url.split("/");
        return getHTML().getObjPath()+File.separator+parts[parts.length-1]+".html";}
    private String setCSS_File(String url){
        String[] parts=url.split("/");
        return getCSS().getObjPath()+File.separator+parts[parts.length-1]+".css";}
    private String setJS_File(String url){
        String[] parts=url.split("/");
        return getJS().getObjPath()+File.separator+parts[parts.length-1]+".js";}
    private String setMedia_File(String url){
        String[] parts=url.split("/");
        return getMedia().getObjPath()+File.separator+parts[parts.length-1]+".img";}
    private String setAudio_File(String url){
        String[] parts=url.split("/");
        return getMedia().getObjPath()+File.separator+parts[parts.length-1]+".mp3";}
}
