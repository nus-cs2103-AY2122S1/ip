package duke.command;

/**
 * Represent all commands used in Duke bot.
 */
public interface Command {
    /**
     * Execute the command with given array of arguments.
     * 
     * @param args arguments of the command
     */
    void exec(String args);
}
