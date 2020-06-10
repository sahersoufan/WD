package sample;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class ALLURL{


    private static final int MAX_DEPTH = 5;
    static List<pair<pair<String,Boolean>,Integer>> Link= new ArrayList<>();
    static List<String>AllLink =new ArrayList<>();
    static int counter=0;


    static boolean Searsh(String string)
    {
        for (int i=0;i<Link.size();i++)
        {
            if(Link.get(i).getKey().getKey().equals(string)||Link.get(i).getKey().getKey().equals(string+"/"))
            {

                return  true;
            }
        }

        return false;
    }
    public static void getLink(String Url,String MainUrl,int depth) throws IOException {



        if(!Filter.FilterExcluded(Url))
        {
            Document doc = Jsoup.connect(Url).get();
            Elements links1 = doc.select("a[href]");
            Elements links2 = doc.select("link[href]");
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            for (Element link : links1) {
                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl(MainUrl, link.attr("href")),false),depth);
                if(!Searsh(Pair.getKey().getKey()))
                {
                    Link.add(Pair);

                }

            }

            for (Element link : links2) {

                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl(MainUrl, link.attr("href")),false),depth);
                if(!Searsh(Pair.getKey().getKey()))
                {
                    Link.add(Pair);

                }
            }


            for (Element image : images) {
                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl(MainUrl, image.attr("href")),false),depth);
                if(!Searsh(Pair.getKey().getKey()))
                {
                    Link.add(Pair);
                }

            }


        }

    }

    public static List<String> getAllLink(String MainUrl) throws IOException {



        Link.add(new pair<>(new pair<>(MainUrl,true),0));
        getLink(MainUrl,MainUrl,1);

        for(int i=0;i<Link.size();i++)
        {

            if((Link.get(i).getValue())<(MAX_DEPTH-1))
            {
                if(!Link.get(i).getKey().getValue())
                {
                    Link.get(i).getKey().setValue(true);
                    getLink(Link.get(i).getKey().getKey(),MainUrl,(Link.get(i).getValue()+1));
                }
            }
            else
                break;


        }


        for (pair<pair<String,Boolean>, Integer> link : Link)
        {
            AllLink.add(link.getKey().getKey());

        }
        for (int i=0;i<AllLink.size();i++)
        {
            System.out.println(AllLink.get(i));
        }

        System.out.println(AllLink.size());

        return AllLink;

    }




}
