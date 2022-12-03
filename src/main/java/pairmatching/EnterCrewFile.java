package pairmatching;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EnterCrewFile {
    public List<String> enterFileToList(String path) throws IOException {
        return fileToList(readFile(path));
    }

    private BufferedReader readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader filereader = new FileReader(file);
        return new BufferedReader(filereader);
    }

    private List<String> fileToList(BufferedReader bufferedReader) throws IOException {
        List<String> crewNames = new ArrayList<>();
        String name;
        
        while((name = bufferedReader.readLine()) != null){
            crewNames.add(name);
        }
        bufferedReader.close();

        return crewNames;
    }
}
