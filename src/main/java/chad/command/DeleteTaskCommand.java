package chad.command;

import chad.task.Task;
import chad.task.TaskHandler;
import chad.ui.Ui;

public class DeleteTaskCommand extends ApplyOnTaskNumberCommand {

    private static final CommandType COMMAND_TYPE = CommandType.DELETE_TASK;

    private Task deletedTask;

    public DeleteTaskCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) {
        try {
            deletedTask = taskHandler.deleteTask(getTaskIndex());
        } catch (IndexOutOfBoundsException e) {
            throw new ChadInvalidCommandException("The task number does not exist.");
        }
        ui.startMessage()
                .addLine("Noted. I've removed this task:")
                .addTask(deletedTask)
                .addTasksListLength(taskHandler.getNumberOfTasks())
                .printFormatted();
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
