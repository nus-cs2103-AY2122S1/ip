package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Represents a list command. A <code>ListCommand</code> returns
 * the list of tasks when a user requests for it.
 */
public class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";

    /**
     * Public constructor of a <code>ListCommand</code>.
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     */
    public ListCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "list  | get the current list of tasks";
    }

    /**
     * Prints the current tasks in the task list.
     */
    @Override
    public String execute() {
        return LIST_MESSAGE + taskList.toString();
    }

}

