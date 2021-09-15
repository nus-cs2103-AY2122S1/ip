package duke.util;

import duke.exception.DukeException;
import duke.taskTypes.Task;

import java.util.List;

public interface Storage {
    List<String> loadSaved() throws DukeException;
    void saveAddedTask(Task task) throws DukeException;
    void saveUpdateTask(TaskList taskList) throws DukeException;
}
