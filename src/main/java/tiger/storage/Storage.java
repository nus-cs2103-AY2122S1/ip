package tiger.storage;

import tiger.components.TaskList;
import tiger.exceptions.storage.TigerStorageInitException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.exceptions.storage.TigerStorageSaveException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    public static final String FILE_PATH = "./data/Tiger.txt";
    public static final String DIRECTORY_PATH = "./data/";

    public static void makeFileIfNotPresent() throws TigerStorageInitException {
        new File(DIRECTORY_PATH).mkdir();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // hopefully it will never reach this line
                throw new TigerStorageInitException(e.toString());
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws TigerStorageSaveException {
        // adapted from: https://nus-cs2103-ay2122s1.github.io/website/schedule/week3/topics.html#c-to-java-miscellaneous-topics-file-access
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new TigerStorageSaveException(e.toString());
        }
    }

    public static void save(TaskList taskList) throws TigerStorageSaveException {
        String textToAdd = taskList.getStorageRepresentation();
        writeToFile(FILE_PATH, textToAdd);
    }

    public static TaskList load() throws TigerStorageLoadException {
        try {
            String textToParse = Files.readString(Paths.get(FILE_PATH), StandardCharsets.US_ASCII);
            return TaskList.getTaskListFromStringRepresentation(textToParse);
        } catch (IOException e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }

    public static TaskList partialLoad() {
        // TODO: implement partial loading
        return new TaskList();
    }

    public static void wipeStorage() {
        writeToFile(FILE_PATH, "");
    }
}
