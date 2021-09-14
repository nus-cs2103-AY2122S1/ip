package duke.command;

/**
 * Encapsulates a command to be run by the system in response to a user input.
 *
 * @author Toh Wang Bin
 */
public interface Command {

    /**
     * Executes the command unique to each Command class.
     *
     * @return A string representing the response to running this command.
     */
    public String execute();

}
