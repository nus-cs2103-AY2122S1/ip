package storage;

import java.io.File;
import java.io.IOException;

import exception.ErrorAccessingFileException;

/**
 * Encapsulates the object handling data that is stored in the hard disk.
 */
public class Storage {
    private final String storageDirectoryPath = "./storage";

    /**
     * Loads storage file containing the list.
     * Creates new storage file for the list if the file does not exist.
     *
     * @return `StorageFile`.
     */
    public StorageFile loadListFile() throws ErrorAccessingFileException {
        try {
            File directory = new File(storageDirectoryPath);
            directory.mkdirs();

            String listFileName = "duke.txt";
            String filePath = String.format("%s/%s", storageDirectoryPath, listFileName);
            return StorageFile.loadFile(filePath);
        } catch (IOException e) {
            throw new ErrorAccessingFileException("load the file");
        }
    }
}
