package duke.command;

import java.util.Arrays;
import java.util.stream.Stream;

import duke.constant.Constants;
import duke.constant.MessageType;
import duke.listener.Message;
import duke.task.TaskList;

/**
 * This is the ComingCommand class that lists tasks.
 */
public class ComingCommand extends Command {
    private static final String COMING_TASK_LISTED_MESSAGE =
            "Here are the coming tasks in your list:";

    /**
     * Constructs a ComingCommand object.
     *
     * @param message Message interface.
     */
    public ComingCommand(Message message) {
        super(message);
    }

    /**
     * Finds coming tasks from TaskList.
     *
     * @param taskList TaskList object.
     */
    @Override
    public void execute(TaskList taskList) {
        getMessage().show(MessageType.NORMAL, Stream.concat(
                Arrays.stream(new String[] {COMING_TASK_LISTED_MESSAGE}),
                Arrays.stream(taskList.findComingTasks(Constants.COMING_TASK_HOUR_RANGE))
        ).toArray(String[]::new));
    }
}
