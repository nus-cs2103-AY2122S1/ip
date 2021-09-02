package duke.commands;

import duke.Storage;
import duke.task.TaskList;

public abstract class Command {

    public abstract String[] execute(Storage storage, TaskList tasks);
}
