package duke.command;

import duke.core.Storage;
import duke.core.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, Storage storage);

    public abstract boolean shouldExit();
}
