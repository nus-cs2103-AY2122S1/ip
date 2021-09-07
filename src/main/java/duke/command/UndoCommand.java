package duke.command;

import duke.task.TaskHandler;
import duke.ui.Ui;

public class UndoCommand extends Command {

    private static final CommandType COMMAND_TYPE = CommandType.ADD_DEADLINE_TASK;

    /**
     * Creates a Command instance.
     *
     * @param command The command represented by the instance.
     */
    public UndoCommand(String command) throws DukeInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws DukeInvalidCommandException {
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.undo(taskHandler);
    }

    @Override
    void parseCommand(String[] tokens) throws DukeInvalidCommandException {

    }

    @Override
    CommandType getCommandType() {
        return null;
    }
}
