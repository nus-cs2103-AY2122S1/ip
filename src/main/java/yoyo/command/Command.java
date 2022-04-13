package yoyo.command;

import java.util.Arrays;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

/**
 * A command class representing an user command.
 */
public abstract class Command {
    protected String[] inputTokens;

    /**
     * Constructor for Command class.
     *
     * @param inputTokens Array of string tokens constructed from user input.
     */
    public Command(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    /**
     * Checks remaining user input for incomplete commands.
     *
     * @param inputTokens String array from user input.
     * @throws YoyoException.YoyoIncompleteCommandException Thrown if command is incomplete.
     */
    public static void checkTwoTokenCommand(String[] inputTokens)
            throws YoyoException.YoyoIncompleteCommandException {
        if (inputTokens.length < 2 || inputTokens[1].trim().length() == 0) {
            throw new YoyoException.YoyoIncompleteCommandException(
                    "You have not entered enough information for your command.");
        }
    }

    /**
     * Executes actions depending on command type.
     *
     * @param tasks         Tasks currently in the Yoyo program.
     * @param storage       Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException When input lack necessary info for commands.
     */
    public abstract String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler)
            throws YoyoException;

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        Command other = (Command) o;
        return Arrays.equals(this.inputTokens, other.inputTokens);
    }

}
