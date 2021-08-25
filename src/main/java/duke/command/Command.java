package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.command.Command;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
    public abstract boolean shouldExit();
}
