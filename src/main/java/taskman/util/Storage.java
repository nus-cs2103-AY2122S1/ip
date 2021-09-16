package taskman.util;

import taskman.exception.DukeException;
import taskman.tasktypes.Task;

import java.util.List;

public interface Storage {
    List<String> loadSaved() throws DukeException;
    void saveAddedTask(Task task) throws DukeException;
    void saveUpdateTask(TaskList taskList) throws DukeException;
}
