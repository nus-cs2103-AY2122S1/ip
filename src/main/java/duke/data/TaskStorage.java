package duke.data;

import duke.tasks.Task;

import java.io.IOException;
import java.util.List;

public interface TaskStorage {
    void saveTasks(List<Task> taskList) throws IOException;

    List<Task> loadTasks() throws IOException;
}
