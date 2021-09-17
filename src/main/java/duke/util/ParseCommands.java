package duke.util;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * An interface that parses commands that executes the tasklist.
 */
public interface ParseCommands {
    String execute(TaskList tasklist) throws DukeException, IOException;
}
