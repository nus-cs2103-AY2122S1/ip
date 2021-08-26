package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
}
