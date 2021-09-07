package duke.command;

import duke.storage.Storage;
import duke.ui.Gui;
import duke.task.TaskList;
import duke.exception.DukeException;

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
