package duke.util;

import duke.task.TaskList;
import duke.exception.DukeException;
import java.io.IOException;

public interface ParseCommands {
    String execute(TaskList tasklist) throws DukeException, IOException;
}
