package chad.command;

import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents an "Undo" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class UndoCommand extends Command {

    private static final CommandType COMMAND_TYPE = CommandType.UNDO;
    private static final String NO_UNDOABLE_COMMANDS_LEFT_MESSAGE = "There are no commands (left over) to undo.";
    private static final String UNDO_SUCCESSFUL_MESSAGE = "Okay, I have undone the following command:";
    private static final String UNNECESSARY_ARGUMENTS_ERROR_MESSAGE = "There were unnecessary arguments.";

    /**
     * Creates an UndoCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public UndoCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException {
        CommandHandler commandHandler = CommandHandler.getInstance();
        Command command = commandHandler.undo(taskHandler);
        if (command == null) {
            ui.startMessage()
                    .addLine(NO_UNDOABLE_COMMANDS_LEFT_MESSAGE)
                    .displayMessage();
        } else {
            ui.startMessage()
                    .addLine(UNDO_SUCCESSFUL_MESSAGE)
                    .addCommand(command)
                    .displayMessage();
        }
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        if (tokens.length > 1) {
            throw new ChadInvalidCommandException(UNNECESSARY_ARGUMENTS_ERROR_MESSAGE);
        }
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
