package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Storage handles saving and loading task lists.
 *
 * @author Gabriel Goh
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructor to create a storage.
     *
     * @param filePath Path to storage file
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Save list of tasks into storage.
     *
     * @param taskList List of tasks
     */
    public void save(TaskList taskList) {
        createIfNotExist(filePath.getParent(), filePath);
        try {
            Files.writeString(filePath, taskList.saveString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create text file and directories to that text file if any of them do not exist.
     *
     * @param dir  Directory that file is contained in
     * @param file Name of text file
     */
    public static void createIfNotExist(Path dir, Path file) {
        if (Files.notExists(dir)) {
            try {
                Files.createDirectories(dir);
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
