package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Gui;

/**
 * An abstract class representing a command
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public abstract class Command {

    public abstract void execute(TaskList taskList, Gui gui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
