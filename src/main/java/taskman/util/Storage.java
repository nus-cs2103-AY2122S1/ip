package taskman.util;

import java.util.List;

import taskman.exception.DukeException;
import taskman.tasktypes.Task;

public interface Storage {
    List<String> loadSaved() throws DukeException;
    void saveAddedTask(Task task) throws DukeException;
    void saveUpdateTask(TaskList taskList) throws DukeException;
}
