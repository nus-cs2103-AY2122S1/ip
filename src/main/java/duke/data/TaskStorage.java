package duke.data;

import java.io.IOException;
import java.util.List;
import duke.tasks.Task;

/**
 * Interface which defines behaviour of the storage used to store tasks.
 *
 * @author kevin9foong
 */
public interface TaskStorage {
    void saveTasks(List<Task> taskList) throws IOException;
    List<Task> loadTasks() throws IOException;
}
