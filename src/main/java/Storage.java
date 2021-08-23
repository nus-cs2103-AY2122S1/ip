import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
/**
 * Contains the logic for file operations.
 */

public class Storage {

    final Path path;

    Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Loads the file at the desired path. If a file is not found,
     * then a new file is created.
     * @return an ArrayList of tasks in the file
     */
    TaskList load() {
        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        } catch (Exception e) {
            System.out.println("Error occurred while loading file");
            e.printStackTrace();
        }
        return new TaskList();
    }

    /**
     * Saves all the task from the current list of tasks to the file.
     * @param taskList the list of tasks to be saved
     */
    void save(TaskList taskList) {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            Files.write(path, encodedTaskList);
        } catch (IOException ioe) {
            System.out.println("Error writing to file");
        }
    }
}
