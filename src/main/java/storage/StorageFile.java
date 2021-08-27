package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tasklist.Task;
import tasklist.TaskList;

/**
 * Encapsulates a storage file that handles data stored in one file in the hard disk.
 */
public class StorageFile {
    private String filePath;
    private File file;

    private StorageFile(String filePath, File file) {
        this.filePath = filePath;
        this.file = file;
    }

    /**
     * Loads a file from the hard disk.
     *
     * @param filePath Location of the file on the hard disk.
     * @return `StorageFile`.
     * @throws IOException If there is an exception in loading the file.
     */
    public static StorageFile loadFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return new StorageFile(filePath, file);
    }

    /**
     * Scans the file data to a given list.
     *
     * @param list App representation of a list.
     * @throws FileNotFoundException If the file cannot be found in the hard disk.
     */
    public void scanFileDataToList(TaskList list) throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        while (s.hasNext()) {
            String lineData = s.nextLine();
            list.scanExistingTaskToList(Task.createTaskFromStoredString(lineData));
        }
    }

    /**
     * Adds item to a new line at the end of the file in the hard disk.
     *
     * @param item Item to be added.
     * @throws IOException If there is an exception when accessing the file.
     */
    public void add(String item) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(item + System.lineSeparator());
        fw.close();
    }

    /**
     * Rewrites the file with new content.
     *
     * @param items Items to rewrite the new file with.
     * @throws IOException If there is an exception when accessing the file.
     */
    public void rewriteFile(List<?> items) throws IOException {
        List<String> lines = new ArrayList<>();
        items.forEach((item) -> lines.add(item.toString()));
        Path path = Paths.get(this.filePath);
        Files.write(path, lines);
    }
}
