package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.task.TaskList;
/**
 * Contains the logic for file operations.
 *
 * @author Benjamin Lui
 */

public class Storage {

    final Path path;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
    }

    /**
     * Loads the file at the desired path. If a file is not found,
     * then a new file is created.
     * @return an ArrayList of tasks in the file
     */
    public TaskList load() throws IOException {
        return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
    }

    /**
     * Saves all the task from the current list of tasks to the file.
     * @param taskList the list of tasks to be saved
     */
    public void save(TaskList taskList) {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            Files.write(path, encodedTaskList);
        } catch (IOException ioe) {
            System.out.println("Error writing to file");
        }
    }

    /**
     * Clears all the task in the current text file.
     */
    public void clear() {
        try {
            TaskList tskLst = this.load();
            tskLst.clearTasks();
            save(tskLst);
        } catch (IOException ioe) {
            return;
        }
    }
}
