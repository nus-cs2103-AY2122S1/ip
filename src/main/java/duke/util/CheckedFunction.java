package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

public interface CheckedFunction {
    String execute(TaskList t) throws DukeException;
}
