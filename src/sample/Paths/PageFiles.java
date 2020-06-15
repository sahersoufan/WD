package sample.Paths;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PageFiles{
    public static final String DOWNLOADING_FILE="downloading.txt";
    String mainPath;
    Path CSS;
    Path HTML;
    Path media;
    Path JS;

    public PageFiles(String mainPath) {
        this.mainPath = mainPath;
        CSS=new CSS(mainPath,"CSS");
        HTML=new HTML(mainPath,"HTML");
        JS=new JS(mainPath,"JS");
        media=new Media(mainPath,"Media");
    }
    public synchronized void saveIn(Path folder,String data,String nameOfFile) throws IOException {
        String fileSave= folder.getObjPath()+File.separator+nameOfFile;
        folder.writeFile(fileSave,data);
    }
    public Path getHTML() {
        return HTML;
    }

    public Path getMedia() {
        return media;
    }

    public Path getJS() {
        return JS;
    }
    public Path getCSS() {
        return CSS;
    }
    public synchronized void setURLS(ArrayList<String> urls,String location,String namePage) throws IOException {
        File folder=new File(location);
        folder.mkdirs();
        File saveURLS=new File(location+File.separator+namePage);
        if (!(saveURLS.exists())){
            boolean safe= saveURLS.createNewFile();
            if (safe)
                System.out.println("Created successful "+location);
            else
                System.err.println("unsafe new file");
        }
        FileOutputStream fos=new FileOutputStream(saveURLS,true);
        PrintWriter pw=new PrintWriter(fos);
        for (String temp:urls){
            pw.println(temp);
        }
        pw.close();
        fos.close();
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
    public synchronized String getOneURL(String pathOfFile) throws IOException {
        FileInputStream fis = new FileInputStream(pathOfFile);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String oneLine = "";
        oneLine=br.readLine();
        br.close();
        isr.close();
        fis.close();
        FileOutputStream fos=new FileOutputStream(DOWNLOADING_FILE);
        PrintWriter pw=new PrintWriter(fos);
        pw.println(oneLine);
        pw.close();
        fos.close();
        deleteFirstLine(pathOfFile);
        return oneLine;
    }
    public synchronized String getOneURL_From_Downloading() throws IOException {
        FileInputStream fis = new FileInputStream(DOWNLOADING_FILE);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String oneLine = "";
        oneLine=br.readLine();
        br.close();
        isr.close();
        fis.close();
        return oneLine;
    }
    public synchronized void  removeOneURL_FromDownloading() throws IOException {
        File path = new File(DOWNLOADING_FILE);
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
        File file = new File(DOWNLOADING_FILE);
        return !file.exists() || file.length() <= 0;
    }
    public synchronized boolean isURL_InURL_Text(String textPath){
        File file=new File(textPath);
        return !file.exists() || file.length() <= 0;
    }
    public long sizeOfFileInKB(String file){
        return file.length()/1024;
    }
    public long sizeOfFileInMB(String file){
        return sizeOfFileInKB(file)/1024;
    }
    public void openInBrowser(String filePath) throws IOException {
        try {
            File htmlFile = new File(filePath);
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (Exception e){
            System.err.println("error to open browser because you insert false path of file that you wont to open");
            System.err.println(e.getMessage());
        }
    }
}