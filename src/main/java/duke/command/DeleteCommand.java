package duke.command;

import duke.constant.MessageType;
import duke.exception.DukeTaskNumberOutOfBoundsException;
import duke.listener.Message;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This is the DeleteCommand class that deletes task.
 */
public class DeleteCommand extends Command {
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task:";

    private final String command;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param command Command from user input.
     * @param message Message interface.
     */
    public DeleteCommand(String command, Message message) {
        super(message);
        this.command = command;
    }

    /**
     * Deletes task by number extracted from command.
     *
     * @param taskList TaskList object.
     */
    @Override
    public void execute(TaskList taskList) {
        int number = getTaskNumber(command);
        if (number <= 0) {
            return;
        }
        try {
            Task task = taskList.deleteTask(number);
            getMessage().show(MessageType.NORMAL,
                    TASK_REMOVED_MESSAGE,
                    "  " + task.toString(),
                    "Now you have " + taskList.getSize()
                            + " " + (taskList.getSize() <= 1 ? "task" : "tasks")
                            + " in the list."
            );
        } catch (DukeTaskNumberOutOfBoundsException e) {
            getMessage().show(MessageType.ERROR, e.getMessage());
        }
    }
}
