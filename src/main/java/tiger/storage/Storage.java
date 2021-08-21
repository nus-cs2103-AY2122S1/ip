package tiger.storage;

import tiger.components.TaskList;
import tiger.exceptions.TigerStorageInitException;
import tiger.exceptions.TigerStorageSaveException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public static final String FILE_PATH = "./data/Tiger.txt";
    public static final String DIRECTORY_PATH = "./data/";
    private TaskList taskList;
    private File file;

    public Storage(TaskList taskList) throws TigerStorageInitException {
        this.taskList = taskList;
        new File(DIRECTORY_PATH).mkdir();
        this.file = new File(FILE_PATH);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
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

    public void save() throws TigerStorageSaveException {
        String textToAdd = this.taskList.getStorageRepresentation();
        writeToFile(FILE_PATH, textToAdd);
    }
}
