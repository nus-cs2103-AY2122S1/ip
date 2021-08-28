package tiger.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import tiger.components.TaskList;
import tiger.exceptions.storage.TigerStorageInitException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.exceptions.storage.TigerStorageSaveException;



public class Storage {

    public static final String FILE_PATH = "./data/Tiger.txt";
    public static final String DIRECTORY_PATH = "./data/";

    /**
     * Makes the storage text file is not present.
     *
     * @throws TigerStorageInitException if there is some {@code IOException}.
     */

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
        // adapted from: https://nus-cs2103-ay2122s1.github.io/website/schedule/week3/
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new TigerStorageSaveException(e.toString());
        }
    }

    /**
     * Saves {@code TaskList} into the storage file.
     *
     * @param taskList a {@code TaskList} object to be saved.
     * @throws TigerStorageSaveException if there is some {@code IOException}.
     */

    public static void save(TaskList taskList) throws TigerStorageSaveException {
        String textToAdd = taskList.getStorageRepresentation();
        writeToFile(FILE_PATH, textToAdd);
    }

    /**
     * Loads {@code TaskList} from the storage file.
     *
     * @return a new {@code TaskList}.
     * @throws TigerStorageLoadException if the storage file has been corrupted in some way,
     *                                   or if there is some {@code IOException}.
     */

    public static TaskList load() throws TigerStorageLoadException {
        try {
            String textToParse = Files.readString(Paths.get(FILE_PATH), StandardCharsets.US_ASCII);
            return TaskList.getTaskListFromStringRepresentation(textToParse);
        } catch (IOException e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }

    /**
     * Loads a subset of {@code TaskList} from what data can be recovered from the storage file.
     *
     * @return a new {@code TaskList}.
     */

    public static TaskList partialLoad() {
        try {
            String textToParse = Files.readString(Paths.get(FILE_PATH), StandardCharsets.US_ASCII);
            return TaskList.getPartialTaskListFromStringRepresentation(textToParse);
        } catch (IOException e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }

    /**
     * Wipes the storage file.
     */

    public static void wipeStorage() {
        writeToFile(FILE_PATH, "");
    }
}
