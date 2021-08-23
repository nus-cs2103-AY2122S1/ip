package duke.command;

import duke.task.TaskHandler;
import duke.ui.Ui;

/**
 * Represents a generic command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class Command {

    boolean mustExit;

    /**
     * Creates a Command instance.
     *
     * @param command The command represented by the instance.
     */
    public Command(String command) throws DukeInvalidCommandException {
        this.mustExit = false;
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        CommandType commandType = getCommandType();
        if (!commandName.equals(commandType.getCommandName())) {
            throw new DukeInvalidCommandException(String.format("This command is not \"%s\".", commandType.getCommandDescription()));
        }
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

    abstract void parseCommand(String[] tokens) throws DukeInvalidCommandException;

    abstract CommandType getCommandType();

    /**
     * Indicates if Duke must exit.
     *
     * @return True if Duke must exit, false otherwise.
     */
    public boolean mustExit() {
        return mustExit;
    }
}
