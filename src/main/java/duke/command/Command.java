package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * An abstract class representing a command
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public abstract class Command {

    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
