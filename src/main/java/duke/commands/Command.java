package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.tasks.TaskList;

public abstract class Command {

    public abstract void executeCommand(TaskList taskList) throws NoSuchTaskException;
}
