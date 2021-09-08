package duke.command;

import java.util.Arrays;
import java.util.stream.Stream;

import duke.constant.MessageType;
import duke.exception.DukeExtractCommandException;
import duke.listener.Message;
import duke.task.TaskList;
import duke.util.CommandUtils;

/**
 * This is the FindCommand class that lists matching tasks by keyword.
 */
public class FindCommand extends Command {
    private static final String MATCHING_TASK_LISTED_MESSAGE =
            "Here are the matching tasks in your list:";

    private final String command;

    /**
     * Constructs a FindCommand object.
     *
     * @param command Command from user input.
     * @param message Message interface.
     */
    public FindCommand(String command, Message message) {
        super(message);
        this.command = command;
    }

    /**
     * Finds tasks by keyword extracted from command.
     *
     * @param taskList TaskList.
     */
    @Override
    public void execute(TaskList taskList) {
        try {
            String keyword = CommandUtils.extractKeyword(command);
            getMessage().show(MessageType.NORMAL, Stream.concat(
                    Arrays.stream(new String[]{MATCHING_TASK_LISTED_MESSAGE}),
                    Arrays.stream(taskList.findTasks(keyword))
            ).toArray(String[]::new));
        } catch (DukeExtractCommandException e) {
            getMessage().show(MessageType.ERROR, e.getMessage());
        }
    }
}
