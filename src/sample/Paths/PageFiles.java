package sample.Paths;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.proccess.ALLURL;
import sample.proccess.Filter;
import sample.proccess.Repair;
import sample.proccess.pair;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PageFiles{
    private static final String DOWNLOADING_FILE="downloading.txt";
    private static final String URLs_FILE="URLs.txt";
    private String mainPath;
    private String lastMainPath;
    private Path CSS;
    private Path HTML;
    private Path media;
    private Path JS;
    private ArrayList<String> pastArray;

    public PageFiles(String mainPath) {

        this.mainPath=mainPath;
        this.lastMainPath=mainPath;
    }

    public synchronized void setURLS(ArrayList<String> urls) throws IOException {
        mainPath=lastMainPath;
        pastArray= new ArrayList<>(urls);
        String[] parts=urls.get(0).split("\\.");
        String base=parts[1];
        mainPath=getMainPath()+"/"+base;
        File folder=new File(getMainPath());
        folder.mkdirs();
        CSS=new CSS(getMainPath(),"CSS");
        HTML=new HTML(getMainPath(),"HTML");
        JS=new JS(getMainPath(),"JS");
        media=new Media(getMainPath(),"Media");
        String pathOfURLS=getMainPath()+"/"+URLs_FILE;
        File saveURLS=new File(pathOfURLS);
        if (!(saveURLS.exists())){
            boolean safe= saveURLS.createNewFile();
            if (safe)
                System.out.println("Created successful "+pathOfURLS);
            else
                System.err.println("unsafe new file");
        }
        String pathOfDownloading=getMainPath()+"/"+DOWNLOADING_FILE;
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
    public synchronized void saveIn(String data, BufferedImage img,InputStream audio,InputStream video, String url) throws IOException {
        if((Boolean) Filter.FilterCss(url))

            getCSS().writeFile(setCSS_File(url),data);

        else if((Boolean) Filter.FilterJs(url))

            getJS().writeFile(setJS_File(url),data);


        else if((Boolean) Filter.FilterImage(url)){
            String[] s = url.split("\\.");
            getMedia().writeImage(setMedia_File(url),img,s[s.length - 1]);

        }else if((Boolean) Filter.FilterVideo(url)){
            getMedia().writeVideo(setMedia_File(url),video);
        }
        else if((Boolean) Filter.FilterAduio(url))

            getMedia().writeAudio(setAudio_File(url),audio);
        else if ((Boolean)Filter.FilterDomain(url))

            getHTML().writeFile(setDomain_File(url),data);
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
        String oneLine=getHTML().readOneLine(getMainPath()+"/"+URLs_FILE);
        getHTML().writeOneLine(getMainPath()+"/"+DOWNLOADING_FILE,oneLine);
        deleteFirstLine(getMainPath()+"/"+URLs_FILE);
        return oneLine;
    }
    public synchronized String getOneURL_From_Downloading() throws IOException {

        return getHTML().readOneLine(getMainPath()+"/"+DOWNLOADING_FILE);
    }
    public synchronized void  removeOneURL_FromDownloading(String url) throws IOException {
        File inputFile = new File(getMainPath()+"/"+DOWNLOADING_FILE);
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
        tempFile.renameTo(new File(getMainPath()+"/"+DOWNLOADING_FILE));

    }
    public synchronized void  removeOneURL_FromURLs() throws IOException {
        File path = new File(getMainPath()+"/"+URLs_FILE);
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
        File file = new File(getMainPath()+"/"+DOWNLOADING_FILE);
        return !file.exists() || file.length() <= 0;
    }
    public synchronized boolean isURL_InURL_Text(){
        File file=new File(getMainPath()+"/"+URLs_FILE);
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
            File htmlFile = new File(getMainPath()+"/"+"index.html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (Exception e){
            System.err.println("error to open browser because you insert false path of file that you wont to open");
            System.err.println(e.getMessage());
        }
    }
    public void openInExplorer() throws IOException {
        File file = new File (getMainPath()+"/");
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
    public void deleteURLs(){
        try
        {
            File f= new File(getMainPath()+"/"+URLs_FILE);
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
            File f= new File(getMainPath()+"/"+DOWNLOADING_FILE);
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
        String path=getMainPath()+"/"+"HTML";
        String[] pathNames;
        File f=new File(path);
        pathNames=f.list();
        String copy;
        ArrayList<String> allLinks;
        ArrayList<String> allLinks1;
        ArrayList<String> allLinksPaste;
        ArrayList<String> tempallLinks;
        ArrayList<String> tempallLinks1;
        assert pathNames != null;
        for (String pathName:pathNames){

            tempallLinks=new ArrayList<>(getPastArray());
            tempallLinks1=new ArrayList<>(ALLURL.getNOEdit());
            for (int i=0;i<tempallLinks.size();i++){
                String one=tempallLinks.get(i);
                String[] parts=one.split("/");
                one=parts[parts.length-1];
                tempallLinks1.set(i,tempallLinks1.get(i));
                tempallLinks.set(i,one);
            }
            copy=getHTML().readFile(path+"/"+pathName);
            pair<List<String>,List<String>> pair=Filter.FilterList(tempallLinks,tempallLinks1);
            allLinks=new ArrayList<>(pair.getKey());
            allLinks1=new ArrayList<>(pair.getValue());
            allLinksPaste= new ArrayList<>(pair.getValue());

            for (int i=0;i<allLinksPaste.size();i++){
                String link=allLinksPaste.get(i);
                String[] parts = link.split("/");
                link=parts[parts.length-1];
                String type= Filter.filterType(link);
                File subFile=new File(getMainPath()+"/"+type);
                String[] subPathNames=subFile.list();
                String paste;
                assert subPathNames != null;
                for (String sub:subPathNames){
                    if (sub.equals(link)){
                        String replace1=getMainPath();
                        replace1 = replace1.replace("\\", "/");
                        paste=replace1+"/"+type+"/"+parts[parts.length-1];
                        allLinksPaste.set(i,paste);
                        break;
                    }
                }
            }

            for (int i=0;i<allLinksPaste.size();i++){
                copy = copy.replaceAll(allLinks1.get(i),allLinksPaste.get(i));
                System.out.println("int:"+allLinks.get(i)+"  path:"+allLinksPaste.get(i));
            }
            File fDelete=new File(path+"/"+pathName);
            fDelete.delete();
            File fNew=new File(path+"/"+pathName);
            getHTML().writeFile(path+"/"+pathName,copy);
        }
    }
    public synchronized int numberOfLinesIn_URLS() throws IOException {
        return getHTML().numberOfLines(getMainPath()+"/"+URLs_FILE);
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
    private ArrayList<String> getPastArray() {
        return pastArray;
    }
    private String setHTML_File(String url){
        String[] parts=url.split("/");
        return getHTML().getObjPath()+"/"+parts[parts.length-1];}
    private String setCSS_File(String url){
        String[] parts=url.split("/");
        return getCSS().getObjPath()+"/"+parts[parts.length-1];}
    private String setJS_File(String url){
        String[] parts=url.split("/");
        return getJS().getObjPath()+"/"+parts[parts.length-1];}
    private String setMedia_File(String url){
        String[] parts=url.split("/");
        return getMedia().getObjPath()+"/"+parts[parts.length-1];}
    private String setAudio_File(String url){
        String[] parts=url.split("/");
        return getMedia().getObjPath()+"/"+parts[parts.length-1];}
    private String setDomain_File(String url){

            String[] parts=url.split("/");
            return getHTML().getObjPath()+"/"+parts[parts.length-1]+".html";}



}

