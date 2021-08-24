package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, Task task);
    public abstract void execute(TaskList taskList, Storage storage);
}
