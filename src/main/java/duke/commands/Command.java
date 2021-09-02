package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {

    public abstract String[] execute(Storage storage, TaskList tasks);
}
