package duke.command;

import duke.task.TaskHandler;
import duke.ui.Ui;

/**
 * Represents a generic command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class Command {

    private static final boolean MUST_EXIT = false;

    private String command;

    /**
     * Creates a Command instance.
     *
     * @param command The command represented by the instance.
     */
    public Command(String command) throws DukeInvalidCommandException {
        this.command = command;
        String[] tokens = command.strip().split(" ");
        assert tokens.length != 0 && tokens[0].length() != 0 : "The String command cannot be empty";
        String commandName = tokens[0];
        CommandType commandType = getCommandType();
        assert commandName.equals(commandType.getCommandName()) : String.format("This command is not \"%s\".",
                commandType.getCommandDescription());
        parseCommand(tokens);
    }

    /**
     * Executes the command.
     *
     * @param taskHandler The Duke TaskHandler instance.
     * @param ui The Duke Ui instance.
     * @throws DukeInvalidCommandException If the command is malformed, or invalid.
     */
    public abstract void execute(TaskHandler taskHandler, Ui ui) throws DukeInvalidCommandException;

    /**
     * Parses the command to check if it is valid and to set up the command for execution.
     *
     * @param tokens The tokens in the original command which were separated by spaces.
     * @throws DukeInvalidCommandException If the command is malformed, or invalid.
     */
    abstract void parseCommand(String[] tokens) throws DukeInvalidCommandException;

    abstract CommandType getCommandType();

    /**
     * Indicates if Duke must exit.
     *
     * @return True if Duke must exit, false otherwise.
     */
    public boolean mustExit() {
        return MUST_EXIT;
    }

    String getTokenSequence(String[] tokens, int inclusiveStart, int exclusiveEnd) {
        StringBuilder tokenSequenceSb = new StringBuilder();
        for (int i = inclusiveStart; i < exclusiveEnd; i++) {
            String token = tokens[i];
            tokenSequenceSb.append(token).append(" ");
        }
        return tokenSequenceSb.toString().strip();
    }

    @Override
    public String toString() {
        return command;
    }
}
