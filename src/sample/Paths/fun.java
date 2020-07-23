package sample.Paths;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public interface fun {
     void createFolder();
     boolean isCreatedFile(String fileName) throws IOException;
    boolean writeFile(String fileName, String data) throws IOException;
    String readFile(String fileName) throws IOException;
    String readOneLine(String fileName) throws IOException;
    boolean writeOneLine(String fileName,String oneLine) throws IOException;
    int numberOfLines(String pathFile) throws IOException;
    public boolean writeImage( String filename, BufferedImage img, String extin) throws IOException;
    public boolean writeAudio( String fileName, InputStream i) throws IOException ;
    public boolean writeVideo( String fileName, InputStream i) throws IOException ;

    }
