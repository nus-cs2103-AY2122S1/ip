package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

public abstract class Command {
    abstract public boolean isExit();
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);
}
