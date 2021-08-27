package command;

import tasklist.TaskList;
import ui.message.ListMessage;

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
        this.list = list;
    }

    /**
     * Gets the output message representing the command is executed.
     *
     * @return `ListMessage`.
     */
    public ListMessage getOutputMessage() {
        return new ListMessage("Here are the tasks in your list:", this.list.toString());
    }
}
