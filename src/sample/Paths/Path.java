package sample.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public abstract class Path implements fun {
    String mainPath;
    String objPath;

    Path(String mainPath, String objPath) {
        this.mainPath = mainPath;
        this.objPath = mainPath+ File.separator+objPath;
    }
    @Override
    public void createFolder(){
        File file=new File(getObjPath());
        file.mkdirs();
    }
    @Override
    public boolean isCreatedFile(String fileName) throws IOException {

        File file=new File(fileName);
        if (!(file.exists())){
            boolean safe= file.createNewFile();
            if (safe){
                System.out.println("Created successful "+fileName);
                return true;}
            else{
                System.err.println("unsafe new file");
                return false;}
        }
        return true;
    }
    @Override
    public boolean writeFile(String fileName, String data) throws IOException {
        boolean test=isCreatedFile(fileName);
        FileOutputStream fos=new FileOutputStream(fileName,true);
        PrintWriter pw=new PrintWriter(fos);
        pw.println(data);
        pw.close();
        fos.close();
        return test;
    }
    @Override
    public boolean writeImage( String fileName, BufferedImage img, String extin)throws IOException {
        boolean test=isCreatedFile(fileName);
        FileOutputStream fos=new FileOutputStream(fileName,true);

        ImageIO.write(img,extin,fos);
        return test;

    }

    @Override
    public boolean writeAudio( String fileName, InputStream i) throws IOException {
        boolean test=isCreatedFile(fileName);
        OutputStream outstream = new FileOutputStream(fileName,true);
        byte[] buffer = new byte[4096];
        int len;
        while ((len = i.read(buffer)) > 0) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();
        return test;
    }

    @Override
    public boolean writeVideo( String fileName, InputStream i) throws IOException {
        boolean test=isCreatedFile(fileName);
        OutputStream outstream = new FileOutputStream(fileName,true);
        byte[] buffer = new byte[4096];
        int len;
        while ((len = i.read(buffer)) > 0) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();
        return test;
    }
    @Override
    public String readFile(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String allText = "";
        String temp = "";
        while ((temp = br.readLine()) != null) {
            allText += temp;
        }
        br.close();
        isr.close();
        fis.close();
        return allText;
    }
    @Override
    public String readOneLine(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String oneLine = "";
        oneLine=br.readLine();
        br.close();
        isr.close();
        fis.close();
        return oneLine;
    }
    @Override
    public boolean writeOneLine(String fileName,String oneLine) throws IOException {

        boolean test=isCreatedFile(fileName);
        FileOutputStream fos = new FileOutputStream(fileName);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(oneLine);
        pw.close();
        fos.close();
        return test;
    }
    @Override
    public int numberOfLines(String pathFile) throws IOException {
        FileInputStream fis = new FileInputStream(pathFile);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        int count=0;
        String temp = "";
        while ((temp = br.readLine()) != null) {
            count ++;
        }
        br.close();
        isr.close();
        fis.close();
        return count;

    }
    public String getMainPath() {
        return mainPath;
    }
    public String getObjPath() {
        return objPath;
    }

}