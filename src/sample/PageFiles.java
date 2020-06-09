package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

public class PageFiles {
    //paths
    public static String path="D:"+File.separator+"Web";
    public static String CSS_PATH=path+File.separator+"CSS";
    public static String HTML_PATH=path+File.separator+"CSS";
    public static String JS_PATH=path+File.separator+"JS";
    public static String MEDIA_PATH=path+File.separator+"media";

    // files
    public static final String HTML_FILE=path+File.separator+"index.html";
    public static final String CSS_FILE=CSS_PATH+File.separator+"css.css";
    public static final String JS_FILE=JS_PATH+File.separator+"JS.js";
    public static final String MEDIA_FILE=JS_PATH+File.separator+"media";

    public static boolean saveFiles(String html) throws IOException {


        File mainFolder=new File(path);
        File htmlFolder=new File(HTML_PATH);
        File cssFolder=new File(CSS_PATH);
        File jsFolder=new File(JS_PATH);
        File mediaFolder=new File(MEDIA_PATH);


        mainFolder.mkdirs();
        htmlFolder.mkdirs();
        cssFolder.mkdirs();
        jsFolder.mkdirs();
        mediaFolder.mkdirs();

        File htmlFile=new File(HTML_FILE);
        if (!(htmlFile.exists())){
           boolean safe= htmlFile.createNewFile();
           if (safe)
               System.out.println("Created successful new file");
           else
               System.err.println("unsafe new file");
        }
        FileOutputStream fos=new FileOutputStream(htmlFile,true);
        PrintWriter pw=new PrintWriter(fos);
        pw.println(html);
        pw.close();
        fos.close();

        FileInputStream fis=new FileInputStream(HTML_FILE);
        InputStreamReader isr=new InputStreamReader(fis);
        BufferedReader br=new BufferedReader(isr);
        String allText="";
        String temp="";
        while ((temp=br.readLine())!=null){
            allText+=temp;
        }
        br.close();
        isr.close();
        fis.close();
        return true;
    }
    public static ArrayList<String> getAllLinks(String html){
        ArrayList<String>allLinks=new ArrayList<>();
        Document doc= Jsoup.parse(html);
        Elements links=doc.select("a[href]");
        for (Element link :links){
            allLinks.add(link.attr("href"));
        }
        return allLinks;
    }
}
