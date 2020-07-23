package sample.proccess;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ALLURL{



    private int MAX_DEPTH;
    List<pair<pair<String,Boolean>,Integer>> Link= new ArrayList<>();
    List<String>AllLink =new ArrayList<>();
    static List<String>NOEdit =new ArrayList<>();


    long size;
    String MainTitle;
    String MainURL;


    public  String getMainTitle() {
        return MainTitle;
    }

    boolean Search(String string) {
        if(string.equals("")) return true;
        for (int i=0;i<Link.size();i++)
        {
            if(Link.get(i).getKey().getKey().equals(string)||Link.get(i).getKey().getKey().equals(string+"/"))
            {

                return  true;
            }
        }

        return false;
    }
    public  void getLink(String Url,String MainUrl,int depth,List<String> Types) throws IOException {



        if(!Filter.FilterExcluded(Url))
        {
            Document doc = null;

                doc = Jsoup.connect(Url).get();
                if(depth==0)
                {
                    MainTitle= doc.title();
                }
                size += doc.outerHtml().length();
                Elements links1 = null;
                Elements links2=null;
                Elements links3=null;
                Elements CSS=null;
                Elements images=null;
                Elements sources=null;
                Elements audio=null;
                if(Types.size()==0){
                    links1 = doc.select("a[href]");
                    links2 = doc.select("link[href]");
                    CSS = doc.select("#mp-itn b a");
                    links3 = doc.select("script[src~=(?i)\\.(js)]");
                    images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
                    sources = doc.select("source[src~=(?i)\\.(mp3)]");
                    audio = doc.select("audio[src~=(?i)\\.(mp3)]");
                    for (Element link : links1) {
                        pair<pair<String, Boolean>, Integer> Pair = new pair<>(new pair<>(Repair.RepairUrl(MainUrl, link.attr("href")), false), depth);
                        if (!Search(Pair.getKey().getKey())) {
                                Link.add(Pair);
                                NOEdit.add(link.attr("href"));



                        }
                    }
                    for (Element link : links2) {

                        pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,link.attr("href")),false),depth);
                        if(!Search(Pair.getKey().getKey()))
                        {
                                Link.add(Pair);
                                NOEdit.add(link.attr("href"));



                        }
                    }

                    for (Element links4 : CSS) {
                        pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,links4.attr("style")),false),depth);
                        if(!Search(Pair.getKey().getKey()))
                        {

                                Link.add(Pair);
                                NOEdit.add(links4.attr("style"));



                        }


                    }

                    for (Element scripts : links3) {
                        pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,scripts.attr("src")),false),depth);
                        if(!Search(Pair.getKey().getKey()))
                        {

                                Link.add(Pair);
                                NOEdit.add(scripts.attr("src"));



                        }
                    }



                    for (Element source : sources) {
                        pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,source.attr("src")),false),depth);
                        if(!Search(Pair.getKey().getKey()))
                        {

                                Link.add(Pair);
                                NOEdit.add(source.attr("src"));



                        }
                    }
                    for (Element source1 : audio) {
                        pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,source1.attr("src")),false),depth);
                        if(!Search(Pair.getKey().getKey()))
                        {

                            Link.add(Pair);
                            NOEdit.add(source1.attr("src"));



                        }
                    }
                    for (Element image : images) {
                        pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,image.attr("src")),false),depth);
                        if(!Search(Pair.getKey().getKey()))
                        {

                                Link.add(Pair);
                                NOEdit.add(image.attr("src"));


                        }

                    }

                }else {
                    for (int i=0;i<Types.size();i++)
                    {
                        if(Types.get(i).equals("HTML")) {
                            links1 = doc.select("a[href]");
                            links2 = doc.select("link[href]");
                            for (Element link : links1) {
                                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,link.attr("href")),false),depth);
                                if(!Search(Pair.getKey().getKey()))
                                {

                                        Link.add(Pair);
                                        NOEdit.add(link.attr("href"));



                                }

                            }

                            for (Element link : links2) {

                                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,link.attr("href")),false),depth);
                                if(!Search(Pair.getKey().getKey()))
                                {
                                        Link.add(Pair);
                                        NOEdit.add(link.attr("href"));



                                }
                            }
                            break;
                        }
                    }
                    for (int i=0;i<Types.size();i++)
                    {
                        if(Types.get(i).equals("JS")){
                            links3 = doc.select("script[src~=(?i)\\.(js)]");
                            for (Element scripts : links3) {
                                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,scripts.attr("src")),false),depth);
                                if(!Search(Pair.getKey().getKey()))
                                {

                                        Link.add(Pair);
                                        NOEdit.add(scripts.attr("src"));



                                }
                            }
                            break;
                        }
                    }
                    for (int i=0;i<Types.size();i++)
                    {
                        if(Types.get(i).equals("CSS")){
                            CSS = doc.select("#mp-itn b a");
                            for (Element links4 : CSS) {
                                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,links4.attr("style")),false),depth);
                                if(!Search(Pair.getKey().getKey()))
                                {

                                        Link.add(Pair);
                                        NOEdit.add(links4.attr("style"));



                                }


                            }
                            break;
                        }
                    }





                    for (int i=0;i<Types.size();i++)
                    {
                        if(Types.get(i).equals("Media")){
                            sources = doc.select("source[src~=(?i)\\.(mp3)]");

                            for (Element source : sources) {

                                pair<pair<String, Boolean>, Integer> Pair = new pair<>(new pair<>(Repair.RepairUrl(MainUrl, source.attr("src")), false), depth);
                                if (!Search(Pair.getKey().getKey())) {
                                        Link.add(Pair);
                                        NOEdit.add(source.attr("src"));



                                }
                            }


                            audio = doc.select("audio[src~=(?i)\\.(mp3)]");

                            for (Element source : audio) {

                                pair<pair<String, Boolean>, Integer> Pair = new pair<>(new pair<>(Repair.RepairUrl(MainUrl, source.attr("src")), false), depth);
                                if (!Search(Pair.getKey().getKey())) {
                                    Link.add(Pair);
                                    NOEdit.add(source.attr("src"));



                                }
                            }

                            images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
                            for (Element image : images) {
                                pair<pair<String,Boolean>,Integer> Pair=new pair<>(new pair<>(Repair.RepairUrl( MainUrl,image.attr("src")),false),depth);
                                if(!Search(Pair.getKey().getKey()))
                                {
                                        Link.add(Pair);
                                        NOEdit.add(image.attr("src"));

                                }

                            }
                            break;
                        }
                    }




                }




        }

    }

    public  List<String> getAllLink(String mainUrl, List<String> Types, int depth) throws IOException {

        MAX_DEPTH = depth;
        String MainDomain=/*Repair.RepairDomain(mainUrl);*/ mainUrl;
        Link.add(new pair<>(new pair<>(MainDomain,true),0));
        NOEdit.add(MainDomain);

        getLink(MainDomain,MainDomain,1,Types);

        for(int i=0;i<Link.size();i++)
        {

            if((Link.get(i).getValue())<(MAX_DEPTH-1))
            {
                if(!Link.get(i).getKey().getValue())
                {

                    Link.get(i).getKey().setValue(true);
                    getLink(Link.get(i).getKey().getKey(),MainDomain,(Link.get(i).getValue()+1),Types);
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

    public  String getMainURL() {
        return MainURL;
    }
    public  static  List<String> getNOEdit() {
        return Filter.FilterUrl(NOEdit);
    }

}




