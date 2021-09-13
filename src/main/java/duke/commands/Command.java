package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Abstract class that specifies the properties of a command.
 */
public abstract class Command {

    /**
     * description attribute of the command.
     */
    protected final String commandDescription;


    /**
     * Initializes the Command class with user input.
     * @param commandDescription String of the user input.
     */
    Command(String commandDescription) {
        this.commandDescription = commandDescription;

    }

    /**
     * returns if the command is an exit command
     * to stop the duke bot from further running.
     * @return boolean if the command is an exit command.
     */
    public abstract boolean isExit();

    /**
     * Does potentially 3 operations of displaying messages to user,
     * adding new task to stored tasks and updating of local file tasks.
     * @param tasks TaskList to update change given by user.
     * @param ui Ui class to display messages to user.
     * @param storage Storage updates each time a command make changes
     * to the existing stored tasks.
     * @throws DukeException if any of the commands come up erroneous.
     * @return String of task executed.
     */
    public abstract String execute(TaskList tasks,
    Ui ui, Storage storage) throws DukeException;

}
