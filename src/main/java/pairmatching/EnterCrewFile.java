package pairmatching;

import java.io.*;

public class EnterCrewFile {
    public void enterFileToList(String path) throws IOException {
        readFile(path);
    }

    private BufferedReader readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader filereader = new FileReader(file);
        return new BufferedReader(filereader);
    }
}
