package duke.commands;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents the class that executes the command "Add". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class AddCommand extends Command {
    protected static final String USAGE = "add todo <description>\n\n"
            + "add deadline <description> /by <YYYY-MM-DD> <24h-timing (optional)>\n\n"
            + "add event <description> /by <YYYY-MM-DD> <24h-timing (optional)>\n\n";
    private final TaskList TASKLIST = new TaskList();
    private String message;

    /**
     * Constructs the instance of an AddCommand.
     *
     * @param description description of an AddCommand as inputted by the user
     */
    public AddCommand(String description) {
        try {
            TaskList currentTask = TASKLIST.add(description);
            assert currentTask != null : "Task was not added successfully";
            message = ("Got it. I have added this task:\n"
                    + currentTask + "\nNow you have "
                    + TaskList.listLength()
                    + " tasks in the list.");
        } catch (DukeException e) {
            message = e.getMessage();
        }
    }

    /**
     * Retrieves the message stored after execution.
     *
     * @return message stored after execution
     */
    @Override
    public String getMessage() {
        return message;
    }
}
