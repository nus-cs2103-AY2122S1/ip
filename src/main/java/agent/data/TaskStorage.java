package agent.data;

import java.util.List;

import agent.exceptions.InvalidTaskDataException;
import agent.exceptions.TaskFileIoException;
import agent.tasks.Task;

/**
 * Interface which defines behaviour of the storage used to store tasks.
 *
 * @author kevin9foong
 */
public interface TaskStorage {
    void saveTasks(List<Task> taskList) throws TaskFileIoException;

    List<Task> loadTasks() throws TaskFileIoException, InvalidTaskDataException;
}
