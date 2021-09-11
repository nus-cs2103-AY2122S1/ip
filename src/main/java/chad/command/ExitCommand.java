package chad.command;

import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents an "Exit" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class ExitCommand extends Command {

    private static final boolean MUST_EXIT = true;
    private static final CommandType COMMAND_TYPE = CommandType.EXIT;

    /**
     * Creates an ExitCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public ExitCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException {
        ui.displayExitMessage();
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {}

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean mustExit() {
        return MUST_EXIT;
    }
}
