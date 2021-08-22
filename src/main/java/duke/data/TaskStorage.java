package duke.data;

import java.io.IOException;
import java.util.List;
import duke.tasks.Task;

public interface TaskStorage {
    void saveTasks(List<Task> taskList) throws IOException;
    List<Task> loadTasks() throws IOException;
}
