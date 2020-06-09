package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Connection {


    public Document connect(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
