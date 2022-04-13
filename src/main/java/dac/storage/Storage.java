package dac.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dac.exception.StorageMissingException;

/**
 * Represents the storage for Duke. Deals with Duke operations with stored data.
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the contents of the storage file.
     * Returns an empty ArrayList if there is no stored content or file does not exist.
     *
     * @return ArrayList of Strings representing Tasks.
     * @throws StorageMissingException If file does not exist and cannot be created.
     */
    public ArrayList<String> getStorageContents() throws StorageMissingException {
        File file = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
                return new ArrayList<>();
            } catch (IOException ex) {
                throw new StorageMissingException(filePath
                        + " cannot be found and cannot be created. "
                        + "Entered tasks will not be saved.");
            }
        }
        ArrayList<String> contents = new ArrayList<>();
        while (scanner.hasNext()) {
            contents.add(scanner.nextLine());
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
