package sample;




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class ALLURL{


    static List<pair<String,Boolean>> Link= new ArrayList<>();
    static List<String>AllLink =new ArrayList<>();
    static int counter=0;


    static boolean Searsh(String string)
    {
        for (int i=0;i<Link.size();i++)
        {
            if(Link.get(i).getKey().equals(string)||Link.get(i).getKey().equals(string+"/"))
            {
                return  true;
            }
        }
        return false;
    }
    public static void getLink(String Url,String MainUrl) throws IOException {

        for(int i=0;i<Link.size();i++)
        {
            System.out.println(Link.get(i).getKey());
        }

        if(!Filter.FiltetImage(Url))
        {
            Document doc = Jsoup.connect(Url).get();
            Elements links1 = doc.select("a[href]");
            Elements links2 = doc.select("link[href]");
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            for (Element link : links1) {
                pair<String,Boolean>Pair=new pair<>(Repair.RepairUrl(MainUrl, link.attr("href")), false);
                if(!Searsh(Pair.getKey()))
                {
                    Link.add(Pair);
                }

            }

            for (Element link : links2) {

                pair<String,Boolean>Pair=new pair<>(Repair.RepairUrl(MainUrl, link.attr("href")), false);
                if(!Searsh(Pair.getKey()))
                {
                    Link.add(Pair);
                }
            }


            for (Element image : images) {
                pair<String,Boolean>Pair=new pair<>(Repair.RepairUrl(MainUrl, image.attr("href")), false);
                if(!Searsh(Pair.getKey()))
                {
                    Link.add(Pair);
                }

            }


        }

    }


    public static List<String> getAllLink(String MainUrl) throws IOException {

        Link.add(new pair<>(MainUrl,true));
        getLink(MainUrl,MainUrl);


        for(int i=0;i<Link.size();i++)
        {

            System.out.println(Link.size());

            if(!Link.get(i).getValue())
            {
                Link.get(i).setValue(true);
                getLink(Link.get(i).getKey(),MainUrl);
            }
        }


        for (pair<String,Boolean>link : Link)
            AllLink.add(link.getKey());

        return AllLink;

    }




}

