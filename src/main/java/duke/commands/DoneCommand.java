package duke.commands;

import duke.exception.DukeException.InvalidIntegerException;
import duke.task.TaskList;

/**
 * Represents the class that executes the command "Done". Responsible for the
 * usage representation as well as message upon successful execution.
 *
 * @author yeo-yiheng
 */
public class DoneCommand extends Command {
    protected static final String USAGE = "done <number index>\n\n";
    private final TaskList TASKLIST = new TaskList();
    private String message;

    /**
     * Constructs the instance of a DoneCommand.
     *
     * @param description description of an DoneCommand as inputted by the user
     */
    public DoneCommand(String description) {
        try {
            int index = Integer.parseInt(description) - 1;
            assert index >= 0 : "Index is not allowed";
            if (TASKLIST.markDone(index)) {
                message = "Nice! I've marked this task as done:\n" + TaskList.getTask(index);
            } else {
                message = new InvalidIntegerException().getMessage();
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
