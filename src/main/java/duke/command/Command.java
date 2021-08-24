package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.NoListException;

public abstract class Command {
    public abstract void exec(TaskList tasks, Ui ui, Storage storage) throws NoListException;

    public boolean isExit() {
        return false;
    }
}
