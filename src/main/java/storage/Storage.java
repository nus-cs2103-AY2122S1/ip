package storage;

import tasklist.TaskList;
import ui.message.ErrorMessage;
import ui.message.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    private final static String storageDirectoryPath = "./storage";

    public static StorageFile loadListFile() {
        try {
            File directory = new File(storageDirectoryPath);
            directory.mkdirs();

            String listFileName = "duke.txt";
            String filePath = String.format("%s/%s", storageDirectoryPath, listFileName);
            return StorageFile.loadFile(filePath);
        } catch (IOException e) {
            Message message = new ErrorMessage("There was a problem in loading the list data");
            message.print();
            return null;
        }
    }

    public static TaskList scanListFileDataToList(StorageFile listFile) {
        try {
            TaskList list = new TaskList(listFile);
            listFile.scanFileDataToList(list);
            return list;
        } catch (FileNotFoundException e) {
            Message message = new ErrorMessage("There was a problem in scanning the storage data to a list");
            message.print();
            return null;
        }
    }
}
