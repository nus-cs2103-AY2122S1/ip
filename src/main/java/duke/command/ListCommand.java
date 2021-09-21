package duke.command;

import java.util.Arrays;
import java.util.stream.Stream;

import duke.constant.MessageType;
import duke.listener.Message;
import duke.task.TaskList;

/**
 * This is the ListCommand class that lists tasks.
 */
public class ListCommand extends Command {
    private static final String TASK_LISTED_MESSAGE = "Here are the tasks in your list:";

    /**
     * Constructs a ListCommand object.
     *
     * @param message Message interface.
     */
    public ListCommand(Message message) {
        super(message);
    }

    /**
     * Prints tasks from TaskList.
     *
     * @param taskList TaskList object.
     */
    @Override
    public void execute(TaskList taskList) {
        getMessage().show(MessageType.NORMAL, Stream.concat(
                Arrays.stream(new String[] {TASK_LISTED_MESSAGE}),
                Arrays.stream(taskList.printTasks())
        ).toArray(String[]::new));
    }
}
