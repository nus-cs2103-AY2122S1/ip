package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    abstract public boolean isExit();
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);
}
