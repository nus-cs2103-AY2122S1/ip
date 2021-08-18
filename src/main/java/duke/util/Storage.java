package duke.util;

import duke.exception.FileNotFoundException;
import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        try {
            File file = new File(filePath);
            boolean directoryExists = !file.getParentFile().mkdir();
            boolean fileExists = !file.createNewFile();
            if (directoryExists && fileExists) {
                return new ArrayList<>();
            }
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    public void write(TaskList tasks) {
    }
}
