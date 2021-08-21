package entity.data;

import entity.list.DukeTask;
import entity.list.DukeTaskList;

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

public class DukeFile {
    private String filePath;
    private File file;

    private DukeFile(String filePath, File file) {
        this.filePath = filePath;
        this.file = file;
    }

    public static DukeFile loadFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return new DukeFile(filePath, file);
    }

    public void scanFileDataToList(DukeTaskList list) throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        while (s.hasNext()) {
            String lineData = s.nextLine();
            list.scanExistingTaskToList(DukeTask.createTaskFromStoredString(lineData));
        }
    }

    public void add(String item) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(item + System.lineSeparator());
        fw.close();
    }

    public void rewriteFileWith(List<?> items) throws IOException {
        List<String> lines = new ArrayList<>();
        items.forEach((item) -> lines.add(item.toString()));
        Path path = Paths.get(this.filePath);
        Files.write(path, lines);
    }
}
