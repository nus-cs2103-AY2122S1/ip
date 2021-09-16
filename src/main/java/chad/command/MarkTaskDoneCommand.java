package chad.command;

import chad.task.Task;
import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents a "Mark Task Done" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class MarkTaskDoneCommand extends ApplyOnTaskNumberCommand {

    private static final CommandType COMMAND_TYPE = CommandType.MARK_TASK_DONE;
    private static final String NO_EXISTING_TASK_NUMBER_ERROR_MESSAGE = "The task number does not exist.";
    private static final String MARK_TASK_DONE_SUCCESSFUL_MESSAGE = "Nice! I've marked this task as done:";

    /**
     * Creates an MarkTaskDoneCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public MarkTaskDoneCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException {
        Task task;
        try {
            task = taskHandler.markTaskDone(getTaskIndex());
        } catch (IndexOutOfBoundsException e) {
            throw new ChadInvalidCommandException(NO_EXISTING_TASK_NUMBER_ERROR_MESSAGE);
        }
        ui.startMessage()
                .addLine(MARK_TASK_DONE_SUCCESSFUL_MESSAGE)
                .addTask(task)
                .displayMessage();
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.addToUndoableCommands(this);
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    @Override
    public void undo(TaskHandler taskHandler) {
        taskHandler.unmarkTaskDone(getTaskIndex());
    }
}
