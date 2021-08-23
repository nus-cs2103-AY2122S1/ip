package duke.command;

import duke.DukeException;
import duke.TaskManager;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws DukeException;
    public boolean isExit() {
        return false;
    }
}