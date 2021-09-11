package chad.command;

import chad.task.TaskHandler;
import chad.ui.Ui;

public class UndoCommand extends Command {

    private static final CommandType COMMAND_TYPE = CommandType.UNDO;

    /**
     * Creates a Command instance.
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
                    .printFormatted();
        } else {
            ui.startMessage()
                    .addLine("Okay, I have undone the following command:")
                    .addCommand(command)
                    .printFormatted();
        }
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {}

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
