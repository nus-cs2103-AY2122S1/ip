package duke.commands;

import duke.DateTimeHandler;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the abstract command class that concrete command implementations extend from
 */
public abstract class Command {
    private String arguments;

    public Command(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns a new command with the provided String as argument. Factory method
     *
     * @param arguments Arguments to be passed into new Command object
     * @return The created Command
     */
    public abstract Command of(String arguments);


    /**
     * Executes the command and returns a String to be printed by Duke
     *
     * @param tl The TaskList object to interact with the list of tasks
     * @param s The Storage object to interact with the output file
     * @param ui The UI object to interact with the user interface
     * @param dth The DateTimeHandler object to interact with date-times.
     * @return The String to be printed.
     * @throws DukeException Contains the error message.
     */
    public abstract String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) throws DukeException;

    /**
     * Returns the prefix of the command
     *
     * @return The command prefix
     */
    public abstract String getCommandPrefix();

    /**
     * Closes the program if true
     *
     * @return Boolean to determine whether or not to close the program
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the arguments for the command
     *
     * @return The command arguments
     */
    public String getArguments() {
        return arguments;
    }
}
