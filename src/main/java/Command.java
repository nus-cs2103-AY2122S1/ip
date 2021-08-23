import yoyoexception.YoyoException;

import static yoyoexception.YoyoException.YoyoIncompleteCommandException;

public abstract class Command {
    protected String[] inputTokens;

    /**
     * Checks remaining user input for incomplete commands.
     *
     * @param inputTokens String array from user input.
     * @throws YoyoException.YoyoIncompleteCommandException Thrown if command is incomplete.
     */
    public static void checkCompleteCommand(String[] inputTokens)
            throws YoyoIncompleteCommandException {
        if (inputTokens.length < 2 || inputTokens[1].trim().length() == 0) {
            throw new YoyoIncompleteCommandException(
                    "You have not entered enough information for your command.");
        }
    }

    Command(String[] inputTokens) {
        this.inputTokens = inputTokens;
    }

    public abstract void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException;

}
