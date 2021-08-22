package duke.data;

import duke.tasks.Task;

import java.io.IOException;
import java.util.List;

/**
 * Interface which defines behaviour of the storage used to store tasks.
 *
 * @author kevin9foong
 */
public interface TaskStorage {
    void saveTasks(List<Task> taskList) throws IOException;

    List<Task> loadTasks() throws IOException;
}
