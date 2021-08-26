package yoyo.command;

import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

import java.util.Arrays;

public abstract class Command {
    protected String[] inputTokens;

    public Command(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    /**
     * Checks remaining user input for incomplete commands.
     *
     * @param inputTokens String array from user input.
     * @throws YoyoException.YoyoIncompleteCommandException Thrown if command is incomplete.
     */
    public static void checkCompleteCommand(String[] inputTokens)
            throws YoyoException.YoyoIncompleteCommandException {
        if (inputTokens.length < 2 || inputTokens[1].trim().length() == 0) {
            throw new YoyoException.YoyoIncompleteCommandException(
                    "You have not entered enough information for your command.");
        }
    }

    public boolean shouldContinueProgram() {
        return true;
    }

    /**
     * Executes actions depending on command type.
     *
     * @param tasks Tasks currently in the Yoyo program.
     * @param storage Storage instance of the Yoyo program.
     * @param ui Ui instance of Yoyo program.
     * @throws YoyoException
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException;

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        Command other = (Command) o;
        return Arrays.equals(this.inputTokens, other.inputTokens);
    }

}
