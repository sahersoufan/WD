package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Connection {


    public Document connect(String url) throws IOException {
        try {
            return Jsoup.connect(url).get();
        }catch (IOException e){
            System.out.print("asd");
        }
        return new Document("hello");
    }
}
