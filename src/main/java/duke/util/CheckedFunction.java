package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * An interface which is similar to Function< TaskList, String >.
 * This is able to handle DukeException.
 */
public interface CheckedFunction {
    String execute(TaskList taskList) throws DukeException;
}
