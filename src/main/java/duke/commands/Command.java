package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Abstract class that specifies the properties of a command
 */
public abstract class Command {
    protected final String command_description;

    /**
     * Initializes the Command class with user input
     * 
     * @param command_description String of the user input
     */
    Command(String command_description) {
        this.command_description = command_description;
    }

    /**
     * returns if the command is an exit command 
     * to stop the duke bot from further running
     * 
     * @return boolean if the command is an exit command
     */
    public abstract boolean isExit();

    /**
     * Does potentially 3 operations of displaying messages to user,
     * adding new task to stored tasks and updating of local file tasks.
     * 
     * @param tasks TaskList to update change given by user
     * @param ui Ui class to display messages to user
     * @param storage Storage updates each time a command make changes
     * to the existing stored tasks
     * @throws DukeException if any of the commands come up erroneous
     */
    public abstract void execute(TaskList tasks, 
    Ui ui, Storage storage) throws DukeException;

}
