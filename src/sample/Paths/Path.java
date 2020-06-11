package sample.Paths;

import java.io.*;


public abstract class Path implements fun {
    String mainPath;
    String objPath;

    protected Path(String mainPath, String objPath) {
        this.mainPath = mainPath;
        this.objPath = mainPath+ File.separator+objPath;
    }
    public void createFolder(){
        File file=new File(getObjPath());
        file.mkdirs();
    }
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

    public boolean writeFile(String fileName, String data) throws IOException {
        boolean test=isCreatedFile(fileName);
        FileOutputStream fos=new FileOutputStream(fileName,true);
        PrintWriter pw=new PrintWriter(fos);
        pw.println(data);
        pw.close();
        fos.close();
        return test;
    }
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
        public String getMainPath() {
        return mainPath;
    }

    public String getObjPath() {
        return objPath;
    }

}
