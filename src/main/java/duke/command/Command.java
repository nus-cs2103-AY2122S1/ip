package duke.command;

/**
 * Represents all commands used in Duke bot.
 */
public interface Command {

    /**
     * Executes the command with given arguments.
     *
     * @param args arguments of the command
     */
    void exec(String args);

    /**
     * Gets the label of the command, which will be used in the command line to call
     * this command.
     *
     * @return label of the command
     */
    String getLabel();

}
