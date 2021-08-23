package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public void save(TaskList taskList) {
        createIfNotExist(filePath.getParent(), filePath);
        try {
            Files.writeString(filePath, taskList.saveString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createIfNotExist(Path dir, Path file) {
        if (Files.notExists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.notExists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
