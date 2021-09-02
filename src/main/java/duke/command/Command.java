package duke.command;

/**
 * Abstract class Command superclasses all commands for Duke.
 */
public abstract class Command {

    /** Method to execute the specific command. */
    public abstract String execute();

    /**
     * Method to check if we are exiting the program.
     *
     * @return boolean that determines if we exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
