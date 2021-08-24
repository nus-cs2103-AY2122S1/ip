package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage);
    public abstract boolean getIsExit();
}
