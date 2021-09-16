package chad.command;

import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents a generic command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class Command {

    private static final boolean MUST_EXIT = false;
    private static final String TOKEN_DELIMITER = " ";

    private String command;

    /**
     * Creates a Command instance.
     *
     * @param command The command represented by the instance.
     */
    public Command(String command) throws ChadInvalidCommandException {
        this.command = command;
        String[] tokens = command.strip().split(TOKEN_DELIMITER);
        assert tokens.length != 0 && tokens[0].length() != 0 : "The String command cannot be empty.";
        String commandName = tokens[0];
        CommandType commandType = getCommandType();
        assert commandName.equals(commandType.getCommandName()) : String.format("This command is not \"%s\".",
                commandType.getCommandDescription());
        parseCommand(tokens);
    }

    /**
     * Executes the command.
     *
     * @param taskHandler The TaskHandler instance.
     * @param ui The Ui instance.
     * @throws ChadInvalidCommandException If the command is malformed, or invalid.
     */
    public abstract void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException;

    /**
     * Parses the command to check if it is valid and to set up the command for execution.
     *
     * @param tokens The tokens in the original command which were separated by spaces.
     * @throws ChadInvalidCommandException If the command is malformed, or invalid.
     */
    abstract void parseCommand(String[] tokens) throws ChadInvalidCommandException;

    abstract CommandType getCommandType();

    /**
     * Indicates if the app must exit.
     *
     * @return True if the app must exit, false otherwise.
     */
    public boolean mustExit() {
        return MUST_EXIT;
    }

    String getTokenSequence(String[] tokens, int inclusiveStart, int exclusiveEnd) {
        StringBuilder tokenSequenceSb = new StringBuilder();
        for (int i = inclusiveStart; i < exclusiveEnd; i++) {
            String token = tokens[i];
            tokenSequenceSb.append(token).append(TOKEN_DELIMITER);
        }
        return tokenSequenceSb.toString().strip();
    }

    @Override
    public String toString() {
        return command;
    }
}
