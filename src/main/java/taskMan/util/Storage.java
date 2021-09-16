package taskMan.util;

import taskMan.exception.DukeException;
import taskMan.taskTypes.Task;

import java.util.List;

public interface Storage {
    List<String> loadSaved() throws DukeException;
    void saveAddedTask(Task task) throws DukeException;
    void saveUpdateTask(TaskList taskList) throws DukeException;
}
