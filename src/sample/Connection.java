package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Connection {


    public Document connect(String url) throws IOException {

            return Jsoup.connect(url).get();

    }
}
