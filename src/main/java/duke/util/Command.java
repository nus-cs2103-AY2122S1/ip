package duke.util;

import duke.exceptions.DukeException;
import duke.task.TaskList;

/**
 * Encapsulates command interface to handle DukeExceptions thrown by bot.
 */
public interface Command {
    String execute(TaskList taskList) throws DukeException;
}
