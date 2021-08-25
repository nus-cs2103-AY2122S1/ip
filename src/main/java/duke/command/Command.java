package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public Boolean isExit() {
        return this instanceof ExitCommand;
    }
}
