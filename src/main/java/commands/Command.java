package commands;

/**
 * An interface representing commands user has supplied to Duke.
 */
public interface Command {

    /**
     * Execute the command.
     */
    void execute();

    /**
     * Returns true if the command is supposed to exit the chat.
     *
     * @return True if the command should exit the chat.
     */
    boolean isExit();

    /**
     * Displays a message to the user when the user supplied an invalid argument
     * to a command. Not all commands require this since some commands has no arguments.
     */
    void invalidArgumentsProvided();
}
