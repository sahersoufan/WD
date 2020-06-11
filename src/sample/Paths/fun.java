package sample.Paths;

import java.io.IOException;

public interface fun {
     void createFolder();
     boolean isCreatedFile(String fileName) throws IOException;
    boolean writeFile(String fileName, String data) throws IOException;
    String readFile(String fileName) throws IOException;
}
