package command;

import message.Message;
import tasklist.TaskList;

/**
 * Encapsulates a list command after it is parsed from the user input.
 */
public class ListCommand extends Command {
    private TaskList list;

    /**
     * Creates a `ListCommand`.
     *
     * @return `ListCommand`.
     */
    public static ListCommand createCommand() {
        return new ListCommand();
    }

    /**
     * Executes a `ListCommand` by retrieving the list.
     *
     * @param list `TaskList` containing all tasks.
     */
    public void execute(TaskList list) {
        this.list = list.getFullList();
    }

    /**
     * Gets the output message representing the command is executed.
     *
     * @return `Message`.
     */
    public Message getOutputMessage() {
        assert list != null : "task list should not be null";

        String prefix = "Here are the tasks in your list:";
        String kaomoji = "ヽ(°〇°)ﾉ";

        return new Message(prefix, this.list.toString(), kaomoji);
    }
}
