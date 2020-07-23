package sample.getUrlFiles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sample.proccess.Filter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class Connection {


    public Document connect(String url) throws IOException {

            return Jsoup.connect(url).get();

    }

    //download web page without jSoup
    public String DownloadWebPage(String url) throws IOException {
            URL u = new URL(url);

            BufferedReader readR = new BufferedReader(new InputStreamReader(u.openStream()));
            String line ;
            StringBuilder page = new StringBuilder();
            while ((line = readR.readLine()) != null) {
                page.append(line).append("\n");
            }
            return page.toString();
    }

    public BufferedImage DownloadImage(String url) throws IOException {
        BufferedImage image = null;
        URL u = new URL(url);
        image = ImageIO.read(u);
        return image;

    }

    public InputStream downloadAudio(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        InputStream is = conn.getInputStream();

        return is;
    }

    public InputStream downloadVideo(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        InputStream is = conn.getInputStream();

        return is;
    }
}
