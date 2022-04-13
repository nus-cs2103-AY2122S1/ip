package storage;

import java.io.File;
import java.io.IOException;

import exception.ErrorAccessingFileException;

/**
 * Encapsulates the object handling data that is stored in the hard disk.
 */
public class Storage {
    private static final String STORAGE_DIRECTORY_PATH = "./storage";

    /**
     * Loads storage file containing the specified list.
     * Creates new storage file for the list if the file does not exist.
     *
     * @param listFileName Name of the file containing the list data.
     * @return `StorageFile`.
     * @throws ErrorAccessingFileException If there is an error accessing the storage file.
     */
    public StorageFile loadListFile(String listFileName) throws ErrorAccessingFileException {
        try {
            File directory = new File(STORAGE_DIRECTORY_PATH);
            directory.mkdirs();

            String filePath = String.format("%s/%s", STORAGE_DIRECTORY_PATH, listFileName);
            return StorageFile.loadFile(filePath);
        } catch (IOException e) {
            throw new ErrorAccessingFileException("load the file");
        }
    }
}
