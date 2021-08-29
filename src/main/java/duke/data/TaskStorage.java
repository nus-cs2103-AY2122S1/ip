package duke.data;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskDataException;
import duke.exceptions.TaskFileIOException;
import duke.tasks.Task;

import java.io.IOException;
import java.util.List;

/**
 * Interface which defines behaviour of the storage used to store tasks.
 *
 * @author kevin9foong
 */
public interface TaskStorage {
    void saveTasks(List<Task> taskList) throws TaskFileIOException;

    List<Task> loadTasks() throws TaskFileIOException, InvalidTaskDataException;
}
