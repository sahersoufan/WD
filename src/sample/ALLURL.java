package sample;




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class ALLURL{


    private  final int MAX_DEPTH = 5;
     List<pair<pair<String,Boolean>,Integer>> Link= new ArrayList<>();
     List<String>AllLink =new ArrayList<>();
     long size;
     String MainTitle;


    public  String getMainTitle() {
        return MainTitle;
    }

     boolean Search(String string)
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
    public  void getLink(String Url,String MainUrl,int depth) throws IOException {



        if(!Filter.FilterExcluded(Url))
        {
            Document doc = Jsoup.connect(Url).get();
            if(depth==0)
            {
                MainTitle= doc.title();
            }
            size += doc.outerHtml().length();
            Elements links1 = doc.select("a[href]");
            Elements links2 = doc.select("link[href]");
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            for (Element link : links1) {
                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl(MainUrl, link.attr("href")),false),depth);
                if(!Search(Pair.getKey().getKey()))
                {
                    Link.add(Pair);

                }

            }

            for (Element link : links2) {

                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl(MainUrl, link.attr("href")),false),depth);
                if(!Search(Pair.getKey().getKey()))
                {
                    Link.add(Pair);

                }
            }


            for (Element image : images) {
                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl(MainUrl, image.attr("href")),false),depth);
                if(!Search(Pair.getKey().getKey()))
                {
                    Link.add(Pair);
                }

            }


        }

    }

    public  List<String> getAllLink(String MainUrl) throws IOException {



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



        return  Filter.FilterUrl(AllLink);

    }

    public  long getSize() {
        return size/1024;
    }
}
