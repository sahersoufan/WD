package sample;



import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Filter {


    static String TypeUrl(String link)
    {
        String Split[]=link.split("\\.");
        return Split[Split.length-1];
    }

    static Boolean FiltetImage(String Url) throws IOException {

        URL url = new URL(Url);
        BufferedImage image = null;


        if(TypeUrl(Url).equals("icon")||TypeUrl(Url).equals("img")||TypeUrl(Url).equals("png")||TypeUrl(Url).equals("jpg")||TypeUrl(Url).equals("gif")||TypeUrl(Url).equals("ico")){
            return true;
        }
        if(ImageIO.read(url)!=null) {
            return true;
        }


        return  false;

    }


    Boolean FiltetHtml(String Url)
    {
        if(TypeUrl(Url).equals("html"))
            return true;
        return false;
    }


    Boolean FilteCss(String Url)
    {
        if(TypeUrl(Url).equals("css"))
            return true;
        return false;
    }


    Boolean FilteJs(String Url)
    {
        if(TypeUrl(Url).equals("js"))
            return true;
        return false;
    }

}

