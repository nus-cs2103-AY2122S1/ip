package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage for Duke. Deals with Duke operations with stored data.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the contents of the storage file.
     *
     * @return ArrayList of Strings representing Tasks.
     * @throws FileNotFoundException If file cannot be found.
     */
    public ArrayList<String> getStorageContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> contents = new ArrayList<>();
        while (s.hasNext()) {
            contents.add(s.nextLine());
        }
        return contents;
    }

    /**
     * Stores content into storage file.
     *
     * @param content Content to be stored.
     * @param append Whether content should be appended to the file, or to overwrite the file.
     * @throws IOException If file could not be written to.
     */
    public void writeToStorage(String content, boolean append) throws IOException {
        FileWriter fw = new FileWriter(filePath, append);
        fw.write(content);
        fw.close();
    }
}
