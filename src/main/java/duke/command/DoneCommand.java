package duke.command;

import duke.constant.MessageType;
import duke.exception.DukeTaskNumberOutOfBoundsException;
import duke.listener.Message;
import duke.task.TaskList;

/**
 * This is the DoneCommand class that completes task.
 */
public class DoneCommand extends Command {
    private static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";

    private final String command;

    /**
     * Constructs a DoneCommand object.
     *
     * @param command Command from user input.
     * @param message Message interface.
     */
    public DoneCommand(String command, Message message) {
        super(message);
        this.command = command;
    }

    /**
     * Completes task by number extracted from command.
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
            taskList.completeTask(number);
            getMessage().show(MessageType.NORMAL,
                    TASK_MARKED_MESSAGE,
                    "  " + taskList.findTaskByNumber(number).toString()
            );
        } catch (DukeTaskNumberOutOfBoundsException e) {
            getMessage().show(MessageType.ERROR, e.getMessage());
        }
    }
}
