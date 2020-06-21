package sample;


<<<<<<< HEAD
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;
=======
>>>>>>> 5e0e7f7de7000e915c599bcd56e0bc508afca1cc
import java.util.List;

public class Filter {


    //get Suffix
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
        if(TypeUrl(Url).equals("exe")||TypeUrl(Url).equals("avi")||TypeUrl(Url).equals("wav")||TypeUrl(Url).equals("mp3")||TypeUrl(Url).equals("wma")||TypeUrl(Url).equals("bin")){
            return true;
        }
        if(TypeUrl(Url).equals("so")||TypeUrl(Url).equals("tar")||TypeUrl(Url).equals("tif")){
            return true;
        }



        return  false;

    }
    //get name page
    public static String getTitlePage(String page) {
        String result=null;
        int  Index0 = page.indexOf("<title>");
        int  Index1 = page.indexOf("</title>");
        if((Index0>0)&&(Index1>0))
        {
            result=page.substring(Index0+7,Index1) ;
        }
        else
            result="nothing title";


        return  result;
    }
    static Boolean UrlForbidden(String Url)  {



    static Boolean UrlForbidden(String Url)  {





        if(TypeUrl(Url).equals("jar")||TypeUrl(Url).equals("war")||TypeUrl(Url).equals("ear")||TypeUrl(Url).equals("mpg")||TypeUrl(Url).equals("wmv")||TypeUrl(Url).equals("ico")){
            return true;
        }
        if(TypeUrl(Url).equals("cab")||TypeUrl(Url).equals("mpeg")||TypeUrl(Url).equals("scm")||TypeUrl(Url).equals("iso")||TypeUrl(Url).equals("dmp")||TypeUrl(Url).equals("dll")){
            return true;
        }
        if(TypeUrl(Url).equals("exe")||TypeUrl(Url).equals("avi")||TypeUrl(Url).equals("wav")||TypeUrl(Url).equals("mp3")||TypeUrl(Url).equals("wma")||TypeUrl(Url).equals("bin")){
            return true;
        }
        if(TypeUrl(Url).equals("so")||TypeUrl(Url).equals("tar")||TypeUrl(Url).equals("tif")||TypeUrl(Url).equals("ttf")||TypeUrl(Url).equals("pcf")||TypeUrl(Url).equals("bdf")||TypeUrl(Url).equals("snf")||TypeUrl(Url).equals("woff")){
            return true;
        }



        return  false;

<<<<<<< HEAD
    }
    public static List<String> FilterUrl( List<String>OldUrl ) {
        List<String>newUrl = new ArrayList<>();
=======

        if(TypeUrl(Url).equals("jar")||TypeUrl(Url).equals("war")||TypeUrl(Url).equals("ear")||TypeUrl(Url).equals("mpg")||TypeUrl(Url).equals("wmv")){
            return true;
        }
        if(TypeUrl(Url).equals("cab")||TypeUrl(Url).equals("mpeg")||TypeUrl(Url).equals("scm")||TypeUrl(Url).equals("iso")||TypeUrl(Url).equals("dmp")||TypeUrl(Url).equals("dll")){
            return true;
        }
        if(TypeUrl(Url).equals("exe")||TypeUrl(Url).equals("avi")||TypeUrl(Url).equals("wav")||TypeUrl(Url).equals("mp3")||TypeUrl(Url).equals("wma")||TypeUrl(Url).equals("bin")){
            return true;
        }
        if(TypeUrl(Url).equals("so")||TypeUrl(Url).equals("tar")||TypeUrl(Url).equals("tif")||TypeUrl(Url).equals("ttf")||TypeUrl(Url).equals("pcf")||TypeUrl(Url).equals("bdf")||TypeUrl(Url).equals("snf")||TypeUrl(Url).equals("woff")){
            return true;
        }



        return  false;

    }

    public static List<String> FilterUrl( List<String>OldUrl )
    {
        List<String>newUrl = null;
>>>>>>> 5e0e7f7de7000e915c599bcd56e0bc508afca1cc
        for (int i=0;i<OldUrl.size();i++)
        {
            if(!UrlForbidden(OldUrl.get(i)))
            {
<<<<<<< HEAD
                newUrl.add(OldUrl.get(i));
=======
                 newUrl.add(OldUrl.get(i));
>>>>>>> 5e0e7f7de7000e915c599bcd56e0bc508afca1cc
                System.out.println(OldUrl.get(i));
            }
        }
        System.out.println(OldUrl.size());
        return newUrl;
    }
<<<<<<< HEAD
=======

>>>>>>> 5e0e7f7de7000e915c599bcd56e0bc508afca1cc
    Boolean FilterHtml(String Url) {
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



        if(TypeUrl(Url).equals("icon")||TypeUrl(Url).equals("img")||TypeUrl(Url).equals("png")||TypeUrl(Url).equals("jpg")||TypeUrl(Url).equals("gif")||TypeUrl(Url).equals("ico")){
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

    public static String filterType(String link){
        if(FilterCss(link)){
            return "CSS";
        }else if(FilterJs(link)){
            return "JS";
        }else if(FilterAduio(link)){
            return "Media";
        }else if(FilterImage(link)){
            return "Media";
        }else{
            return "HTML";
        }


    }
}
