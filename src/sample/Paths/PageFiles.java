package sample.Paths;
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

    public PageFiles(String mainPath) throws IOException {
        this.mainPath = mainPath;
        CSS=new CSS(mainPath,"CSS");
        HTML=new HTML(mainPath,"HTML");
        JS=new JS(mainPath,"JS");
        media=new Media(mainPath,"Media");
    }
    public void saveIn(Path folder,String data,String nameOfFile) throws IOException {
       String save= folder.getObjPath()+File.separator+nameOfFile;
        folder.writeFile(save,data);
        String temp=folder.readFile(save);
        System.out.println(temp);
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
    public void setURLS(String urls,String location,String nameOfFile) throws IOException {
        File folder=new File(location);
        folder.mkdirs();
        File saveURLS=new File(location+File.separator+nameOfFile);
        if (!(saveURLS.exists())){
            boolean safe= saveURLS.createNewFile();
            if (safe)
                System.out.println("Created successful "+location);
            else
                System.err.println("unsafe new file");
        }
        FileOutputStream fos=new FileOutputStream(saveURLS,true);
        PrintWriter pw=new PrintWriter(fos);
        pw.println(urls);
        pw.close();
        fos.close();
    }
    public void deleteFirstLine(String file) throws IOException {
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
    public String getOneURL(String nameOfFile) throws IOException {
        FileInputStream fis = new FileInputStream(nameOfFile);
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
        deleteFirstLine(nameOfFile);
        return oneLine;
    }
    public String getOneURL_From_Downloading() throws IOException {
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
    public void removeOneURL_FromDownloading() throws IOException {
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
    public boolean isURL_InDownloading() throws IOException {
        File file = new File(DOWNLOADING_FILE);
        return !file.exists() || file.length() <= 0;
    }


    public boolean isURL_InURL_Text(String nameOfFile){
        File file=new File(nameOfFile);
        return !file.exists() || file.length() <= 0;
    }
    public long sizeOfFile(String file){
        return file.length();
    }
}
