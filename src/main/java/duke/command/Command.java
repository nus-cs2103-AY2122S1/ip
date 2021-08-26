package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

public abstract class Command {

    /** Executes the user command */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /** Returns true if the command is an exit command */
    public abstract boolean isExit();
}
