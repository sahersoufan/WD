package sample.getUrlFiles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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
            while ((line = readR.readLine()) != null){
                page.append(line).append("\n");
            }
            return page.toString();
    }
}
