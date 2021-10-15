package duke.commands;
import duke.utils.Storage;
import duke.utils.Ui;
import duke.utils.TaskList;


/**
 * Represent a action to be executed.
 */
public abstract class Command {
    /**
     * executes a given command. Differs from command to command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
    /**
     * Allows duke to pick up an exit command
     */
    public abstract boolean isExit();
}