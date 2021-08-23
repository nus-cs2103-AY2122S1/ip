package duke.command;

import duke.task.Task;
import duke.task.TaskHandler;
import duke.ui.Ui;

/**
 * Represents a "Mark Task Done" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class MarkTaskDoneCommand extends ApplyOnTaskNumberCommand {

    private static final CommandType COMMAND_TYPE = CommandType.MARK_TASK_DONE;

    /**
     * Creates an MarkTaskDoneCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public MarkTaskDoneCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) {
        Task task;
        try {
            task = taskHandler.markTaskDone(getTaskIndex());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException("The task number does not exist.");
        }
        ui.startMessage()
                .addLine("Nice! I've marked this task as done:")
                .addTask(task)
                .printFormatted();
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
