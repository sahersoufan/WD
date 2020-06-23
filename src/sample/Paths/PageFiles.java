package sample.Paths;
import sample.Filter;

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
        String path=getMainPath()+File.separator+URLs_FILE;
        File saveURLS=new File(path);
        if (!(saveURLS.exists())){
            boolean safe= saveURLS.createNewFile();
            if (safe)
                System.out.println("Created successful "+path);
            else
                System.err.println("unsafe new file");
        }

        for (String temp:urls){
            getHTML().writeFile(path,temp);
        }
        deleteFirstLine(path);
    }
    public synchronized void saveIn(String data) throws IOException {

        if((Boolean) Filter.FilterCss(data))
            getCSS().writeFile(setCSS_File(data),data);
        else if((Boolean) Filter.FilterJs(data))
            getJS().writeFile(setJS_File(data),data);
        else if((Boolean) Filter.FilterImage(data))
            getMedia().writeFile(setMedia_File(data),data);
        else
            getHTML().writeFile(setHTML_File(data),data);
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
    public long sizeOfFileInKB(String file){
        return file.length()/1024;
    }
    public long sizeOfFileInMB(String file){
        return sizeOfFileInKB(file)/1024;
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
    private String setHTML_File(String name){return getHTML().getObjPath()+File.separator+Filter.getTitlePage(name)+".html";}
    private String setCSS_File(String name){return getCSS().getObjPath()+File.separator+Filter.getTitlePage(name)+".css";}
    private String setJS_File(String name){return getJS().getObjPath()+File.separator+Filter.getTitlePage(name)+".js";}
    private String setMedia_File(String name){return getMedia().getObjPath()+File.separator+Filter.getTitlePage(name)+".img";}
}
