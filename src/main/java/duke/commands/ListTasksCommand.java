package duke.commands;

import duke.task.TaskList;

/**
 * Represents the class that executes the command "List". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class ListTasksCommand extends Command {
    protected static final String USAGE = "list\n\n";
    private final String MESSAGE_HEADER = "\nHere are the tasks in your list:\n--------------------\n";
    private String message;

    /**
     * Constructs the instance of a ListTaskCommand.
     */
    public ListTasksCommand() {
        message = TaskList.displayList();
    }

    /**
     * Retrieves the message stored after execution.
     *
     * @return message stored after execution
     */
    @Override
    public String getMessage() {
        return MESSAGE_HEADER + message;
    }
}
