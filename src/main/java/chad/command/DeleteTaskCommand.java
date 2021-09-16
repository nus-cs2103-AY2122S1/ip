package chad.command;

import chad.task.Task;
import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents a "Delete Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DeleteTaskCommand extends ApplyOnTaskNumberCommand {

    private static final CommandType COMMAND_TYPE = CommandType.DELETE_TASK;
    private static final String NO_EXISTING_TASK_NUMBER_ERROR_MESSAGE = "The task number does not exist.";
    private static final String DELETE_TASK_SUCCESSFUL_MESSAGE = "Noted. I've removed this task:";

    private Task deletedTask;

    /**
     * Creates a DeleteTaskCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public DeleteTaskCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException {
        try {
            deletedTask = taskHandler.deleteTask(getTaskIndex());
        } catch (IndexOutOfBoundsException e) {
            throw new ChadInvalidCommandException(NO_EXISTING_TASK_NUMBER_ERROR_MESSAGE);
        }
        ui.startMessage()
                .addLine(DELETE_TASK_SUCCESSFUL_MESSAGE)
                .addTask(deletedTask)
                .addTasksListLength(taskHandler.getNumberOfTasks())
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
        taskHandler.insertTask(getTaskIndex(), deletedTask);
    }
}
