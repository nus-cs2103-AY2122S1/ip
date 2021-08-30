package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;
import java.io.IOException;

/**
 * An interface that parses commands that executes the tasklist.
 */
public interface ParseCommands {
    String execute(TaskList tasklist) throws DukeException, IOException;
}
