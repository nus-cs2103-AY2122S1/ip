package duke.command;

import duke.constant.MessageType;
import duke.listener.Message;
import duke.task.TaskList;

/**
 * This is the ClearCommand class that clears all tasks.
 */
public class ClearCommand extends Command {
    private static final String TASK_CLEARED_MESSAGE = "All tasks are cleared.";

    /**
     * Constructs a ClearCommand object.
     *
     * @param message Message interface.
     */
    public ClearCommand(Message message) {
        super(message);
    }

    /**
     * Clears all tasks.
     *
     * @param taskList TaskList object.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.clearTasks();
        getMessage().show(MessageType.NORMAL, TASK_CLEARED_MESSAGE);
    }
}
