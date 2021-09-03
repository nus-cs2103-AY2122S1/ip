package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import message.ErrorMessage;
import message.Message;
import tasklist.TaskList;

/**
 * Encapsulates the object handling data that is stored in the hard disk.
 */
public class Storage {
    private static final String STORAGE_DIRECTORY_PATH = "./storage";

    /**
     * Loads storage file containing the list.
     * Creates new storage file for the list if the file does not exist.
     *
     * @return `StorageFile`.
     */
    public static StorageFile loadListFile() {
        try {
            File directory = new File(STORAGE_DIRECTORY_PATH);
            directory.mkdirs();

            String listFileName = "duke.txt";
            String filePath = String.format("%s/%s", STORAGE_DIRECTORY_PATH, listFileName);
            return StorageFile.loadFile(filePath);
        } catch (IOException e) {
            Message message = new ErrorMessage("There was a problem in loading the list data");
            message.print();
            return null;
        }
    }

    /**
     * Scan data from the storage list to app representation of the list.
     *
     * @param storageFile Storage file containing the list data.
     * @return App representation of the list.
     */
    public static TaskList scanListFileDataToList(StorageFile storageFile) {
        try {
            TaskList list = new TaskList(storageFile);
            storageFile.scanFileDataToList(list);
            return list;
        } catch (FileNotFoundException e) {
            Message message = new ErrorMessage("There was a problem in scanning the storage data to a list");
            message.print();
            return null;
        }
    }
}
