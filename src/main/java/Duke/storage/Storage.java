package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> getStorageContents() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<String> contents = new ArrayList<>();
        while (s.hasNext()) {
            contents.add(s.nextLine());
        }
        return contents;
    }

    public void writeToStorage(String content, boolean append) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, append);
        fw.write(content);
        fw.close();
    }
}
