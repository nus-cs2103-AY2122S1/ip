package command;

import message.Message;
import tasklist.TaskList;

/**
 * Encapsulates a list command after it is parsed from the user input.
 */
public class ListCommand extends Command {
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
     * @return Message representing the command is executed.
     */
    public Message execute(TaskList list) {
        TaskList fullList = list.getFullList();
        return getOutputMessage(fullList);
    }

    private Message getOutputMessage(TaskList list) {
        assert list != null : "task list should not be null";

        if (list.isActiveListEmpty()) {
            return new Message("There are no tasks in the list yet!");
        }

        String prefix = "Here are the tasks in your list:";
        return new Message(prefix, list.toString());
    }
}
