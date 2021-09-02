package duke.command;

/**
 * Class that encapsulates the command inputted by the user.
 *
 * @author Benedict Chua
 */
public abstract class Command {
    /**
     * Returns a boolean to mark if the command is going to cause the program to exit.
     *
     * @return boolean of whether the program will exit with this command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and runs any methods that is related to the command.
     *
     * @return the String result of the execution of the command.
     */
    public abstract String execute();
}
