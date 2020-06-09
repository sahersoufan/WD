package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class URL {


    public static ArrayList<String> getAllLink(String MainUrl) throws IOException {

        ArrayList<String>AllLink=new ArrayList<>();

        Document doc = Jsoup.connect(MainUrl).get();
        Elements links = doc.select("a[href]");
        Elements imports = doc.select("link[href]");


        for (Element link : links) {
            AllLink.add(link.attr("href"));
            System.out.println("link:"+link.attr("href"));
            System.out.println("text : " + link.text());
        }

        for (Element link : imports) {
            AllLink.add(link.attr("href"));
            System.out.println("link:"+link.attr("href"));
        }



        return AllLink;
    }

    public static ArrayList<String> getAllImage(String MainUrl) throws IOException {

        ArrayList<String>AllImage=new ArrayList<>();
        Document doc = Jsoup.connect(MainUrl).get();
        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        for (Element image : images) {
            AllImage.add(image.attr("href"));
            System.out.println("src : " + image.attr("src"));

        }
        return AllImage;
    }


}
