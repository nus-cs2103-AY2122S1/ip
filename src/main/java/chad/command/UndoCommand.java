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
                    .addLine("There are no commands (left over) to undo.")
                    .displayMessage();
        } else {
            ui.startMessage()
                    .addLine("Okay, I have undone the following command:")
                    .addCommand(command)
                    .displayMessage();
        }
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        if (tokens.length > 1) {
            throw new ChadInvalidCommandException("There were unnecessary arguments.");
        }
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
