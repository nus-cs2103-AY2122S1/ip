package duke.commands;

/**
 * A class encapsulating the results of executing a Command.
 */
public class CommandResult {
    private String msg;
    private Boolean isExit;

    /**
     * Instantiates a new CommandResult.
     *
     * @param msg The returned String response from executing a Command.
     * @param isExit A Boolean indicating if the program should terminate.
     */
    public CommandResult(String msg, Boolean isExit) {
        this.msg = msg;
        this.isExit = isExit;
    }

    /**
     * An alternative constructor for a CommandResult, when the program should not terminate.
     *
     * @param msg The returned String response from executing a Command.
     */
    public CommandResult(String msg) {
        this.msg = msg;
        this.isExit = false;
    }

    /**
     * Gets the returned String response from executing a Command.
     *
     * @return The returned String response from executing a Command.
     */
    public String getMessage() {
        return this.msg;
    }

    /**
     * Gets a Boolean indicating if the program should terminate.
     *
     * @return A boolean indicating if the program should terminate.
     */
    public Boolean getExitStatus() {
        return this.isExit;
    }
}
