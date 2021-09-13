package duke.command;

import duke.Storage;
import duke.task.TaskList;

public abstract class Command {

    public abstract String execute(TaskList tasks, Storage storage);
}
