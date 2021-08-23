package duke.command;

import duke.task.Task;
import duke.task.TaskHandler;
import duke.ui.Ui;

public class DeleteTaskCommand extends ApplyOnTaskNumberCommand {

    private static final CommandType COMMAND_TYPE = CommandType.DELETE_TASK;

    public DeleteTaskCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) {
        Task task;
        try {
            task = taskHandler.deleteTask(getTaskIndex());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException("The task number does not exist.");
        }
        ui.startMessage()
                .addLine("Noted. I've removed this task:")
                .addTask(task)
                .addTasksListLength(taskHandler.getNumberOfTasks())
                .printFormatted();
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
