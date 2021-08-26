package duke.command;

import java.io.IOException;

import duke.exception.NoSuchTaskException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** An abstract class representing the user's commands. */
public abstract class Command {
    /**
     * Executes the Command.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The UI object.
     * @param storage The storage utility.
     * @throws IOException If there is an IO exception.
     * @throws NoSuchTaskException If no tasks are found.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoSuchTaskException;

    /** Returns true if the Command is an exit command, else false.*/
    public abstract boolean isExit();
}
