package sample.proccess;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Filter {


    static String TypeUrl(String link) {
        String Split[]=link.split("\\.");
        return Split[Split.length-1];
    }
    static Boolean FilterExcluded(String Url)  {



        if(TypeUrl(Url).equals("icon")||TypeUrl(Url).equals("img")||TypeUrl(Url).equals("png")||TypeUrl(Url).equals("jpg")||TypeUrl(Url).equals("gif")||TypeUrl(Url).equals("ico")){
            return true;
        }

        if(TypeUrl(Url).equals("jar")||TypeUrl(Url).equals("bmp")||TypeUrl(Url).equals("war")||TypeUrl(Url).equals("ear")||TypeUrl(Url).equals("mpg")||TypeUrl(Url).equals("wmv")){
            return true;
        }
        if(TypeUrl(Url).equals("cab")||TypeUrl(Url).equals("mpeg")||TypeUrl(Url).equals("scm")||TypeUrl(Url).equals("iso")||TypeUrl(Url).equals("dmp")||TypeUrl(Url).equals("dll")){
            return true;
        }
        if(TypeUrl(Url).equals("exe")||TypeUrl(Url).equals("avi")||TypeUrl(Url).equals("wav")||TypeUrl(Url).equals("mp3")||TypeUrl(Url).equals("mp4")||TypeUrl(Url).equals("wma")||TypeUrl(Url).equals("bin")){
            return true;
        }
        if(TypeUrl(Url).equals("so")||TypeUrl(Url).equals("tar")||TypeUrl(Url).equals("tif")||TypeUrl(Url).equals("js")||TypeUrl(Url).equals("css")){
            return true;
        }

        return  false;

    }


    static Boolean UrlForbidden(String Url)  {





        if(TypeUrl(Url).equals("jar")||TypeUrl(Url).equals("war")||TypeUrl(Url).equals("ear")||TypeUrl(Url).equals("wmv")||TypeUrl(Url).equals("php")){
            return true;
        }
        if(TypeUrl(Url).equals("cab")||TypeUrl(Url).equals("scm")||TypeUrl(Url).equals("iso")||TypeUrl(Url).equals("dmp")||TypeUrl(Url).equals("dll")){
            return true;
        }
        if(TypeUrl(Url).equals("exe")||TypeUrl(Url).equals("avi")||TypeUrl(Url).equals("wma")||TypeUrl(Url).equals("bin")||TypeUrl(Url).equals("zip")){
            return true;
        }
        if(TypeUrl(Url).equals("so")||TypeUrl(Url).equals("tar")||TypeUrl(Url).equals("tif")||TypeUrl(Url).equals("ttf")||TypeUrl(Url).equals("pcf")||TypeUrl(Url).equals("bdf")||TypeUrl(Url).equals("snf")||TypeUrl(Url).equals("woff")){
            return true;
        }
        return  false;
    }




    static Boolean ISURLValid(String Url) {
        try {
            URL obj=new URL(Url);
            obj.toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e){
            return false;
        }
    }
    public static List<String> FilterUrl(List<String>OldUrl ) {
        List<String>newUrl = new ArrayList<>();
        for (int i=0;i<OldUrl.size();i++)
        {
            if(!UrlForbidden(OldUrl.get(i)))
            {

                newUrl.add(OldUrl.get(i));


                System.out.println(OldUrl.get(i));
            }
        }
        System.out.println(OldUrl.size());
        return newUrl;

    }


    static String getTitlePage(String page) {
        int  Index0 = page.indexOf("<title>");
        int  Index1 = page.indexOf("</title>");
        String result=page.substring(Index0+7,Index1);

        return  result;
    }
    static Boolean FilterHtml(String Url) {
        if(TypeUrl(Url).equals("html"))
            return true;
        return false;
    }
    public static Boolean FilterCss(String Url) {
        if(TypeUrl(Url).equals("css"))
            return true;
        return false;
    }
    public static Boolean FilterJs(String Url) {
        if(TypeUrl(Url).equals("js"))
            return true;
        return false;
    }
    public static Boolean FilterImage(String Url) {



        if(TypeUrl(Url).equals("icon")||TypeUrl(Url).equals("img")||TypeUrl(Url).equals("png")||TypeUrl(Url).equals("jpg")||TypeUrl(Url).equals("gif")){
            return true;
        }

        if(TypeUrl(Url).equals("bmp")||TypeUrl(Url).equals("mpg")){
            return true;
        }

        return  false;

    }
    public static Boolean FilterAduio(String Url) {

        if(TypeUrl(Url).equals("mp3")||TypeUrl(Url).equals("wav")){
            return true;
        }
        return false;
    }
    public static Boolean FilterVideo(String Url) {

        if(TypeUrl(Url).equals("mp4")){
            return true;
        }
        return false;
    }
    public static Boolean FilterDomain(String Url) {

        return (!FilterCss(Url)) && (!FilterJs(Url)) && (!FilterAduio(Url)) && (!FilterImage(Url)) && (!FilterHtml(Url));
    }

    public static String filterType(String link){
        if(FilterCss(link)){
            return "CSS";
        }else if(FilterJs(link)){
            return "JS";
        }else if(FilterAduio(link)){
            return "Media";
        }else if(FilterImage(link)){
            return "Media";
        }
        else{
            return "HTML";
        }
    }
    static boolean Search(List<String> Link, String s) {
        for (int i=0;i<Link.size();i++)
        {
            if(Link.get(i).equals(s))
            {
                return  true;
            }
        }

        return false;
    }
    public  static  pair<List<String>,List<String>> FilterList(List<String> list1,List<String> list2) {
        List<String> newList1=new ArrayList<>();
        List<String> newList2=new ArrayList<>();
        for(int i=0;i<list1.size();i++)
        {
         if(!Search(newList1,list1.get(i))) {
             newList1.add(list1.get(i));
             newList2.add(list2.get(i));
         }
        }

      return new pair<>(newList1,newList2);
    }


}
