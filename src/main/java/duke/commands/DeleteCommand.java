package duke.commands;

import duke.exception.DukeException.InvalidIntegerException;
import duke.task.TaskList;

/**
 * Represents the class that executes the command "Delete". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class DeleteCommand extends Command {
    protected static final String USAGE = "delete <number index>\n\n";
    private final TaskList TASKLIST = new TaskList();
    private String message;

    /**
     * Constructs the instance of a DeleteCommand.
     *
     * @param description description of a DeleteCommand as inputted by the user
     */
    public DeleteCommand(String description) {
        try {
                int index = Integer.parseInt(description) - 1;
                TaskList deletedTask = TaskList.getTask(index);
                if (deletedTask == null) {
                    message = new InvalidIntegerException().getMessage();
                } else {
                    TASKLIST.delete(index);
                    message = "Noted. I've removed this task:\n"
                            + deletedTask + "\nNow you have " + TaskList.listLength() + " tasks in the list.";
                }
            } catch (NumberFormatException e) {
                message = new InvalidIntegerException().getMessage();
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

