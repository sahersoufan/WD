package sample.Paths;

import java.io.IOException;

public interface fun {
     void createFolder();
     boolean isCreatedFile(String fileName) throws IOException;
    boolean writeFile(String fileName, String data) throws IOException;
    String readFile(String fileName) throws IOException;
    String readOneLine(String fileName) throws IOException;
    boolean writeOneLine(String fileName,String oneLine) throws IOException;
    int numberOfLines(String pathFile) throws IOException;
}
