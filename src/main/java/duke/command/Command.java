package duke.command;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {

    protected final Storage storage;
    protected final TaskList taskList;
    protected final Ui ui;

    public Command(Storage storage,TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract boolean exec() throws DukeException;
}
