package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDirectoryException;

public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws DukeException, InvalidDirectoryException;
    public abstract boolean isExit();
}
