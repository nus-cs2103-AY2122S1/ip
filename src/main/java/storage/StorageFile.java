package storage;

import tasklist.Task;
import tasklist.TaskList;

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

public class StorageFile {
    private String filePath;
    private File file;

    private StorageFile(String filePath, File file) {
        this.filePath = filePath;
        this.file = file;
    }

    public static StorageFile loadFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return new StorageFile(filePath, file);
    }

    public void scanFileDataToList(TaskList list) throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        while (s.hasNext()) {
            String lineData = s.nextLine();
            list.scanExistingTaskToList(Task.createTaskFromStoredString(lineData));
        }
    }

    public void add(String item) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(item + System.lineSeparator());
        fw.close();
    }

    public void rewriteFile(List<?> items) throws IOException {
        List<String> lines = new ArrayList<>();
        items.forEach((item) -> lines.add(item.toString()));
        Path path = Paths.get(this.filePath);
        Files.write(path, lines);
    }
}
