package sample;




public class Filter {


    static String TypeUrl(String link)
    {
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


    Boolean FilterHtml(String Url)
    {
        if(TypeUrl(Url).equals("html"))
            return true;
        return false;
    }


    Boolean FilterCss(String Url)
    {
        if(TypeUrl(Url).equals("css"))
            return true;
        return false;
    }


    Boolean FilterJs(String Url)
    {
        if(TypeUrl(Url).equals("js"))
            return true;
        return false;
    }

    static Boolean FilterImage(String Url) {



        if(TypeUrl(Url).equals("icon")||TypeUrl(Url).equals("img")||TypeUrl(Url).equals("png")||TypeUrl(Url).equals("jpg")||TypeUrl(Url).equals("gif")||TypeUrl(Url).equals("ico")){
            return true;
        }

        if(TypeUrl(Url).equals("bmp")||TypeUrl(Url).equals("mpg")){
            return true;
        }

        return  false;

    }
    static Boolean FilterAduio(String Url) {

        if(TypeUrl(Url).equals("mp3")||TypeUrl(Url).equals("wav")){
            return true;
        }
        return false;
    }

}
