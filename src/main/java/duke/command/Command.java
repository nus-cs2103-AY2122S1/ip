package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    public boolean isExit = false;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
