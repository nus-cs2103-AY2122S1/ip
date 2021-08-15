package duke.command;

/**
 * Represent all commands used in Duke bot.
 */
public interface Command {
    /**
     * Returns the name of the command, which is equivalent to the input string to
     * call this.
     * 
     * @return name of command
     */
    String getName();

    /**
     * Execute the command with given array of arguments.
     * 
     * @param args arguments of the command
     */
    void exec(String args);
}
