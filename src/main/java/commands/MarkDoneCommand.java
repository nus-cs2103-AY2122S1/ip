package commands;
import tasks.Task;
import tasks.TaskList;
import exceptions.DukeException;

/**
 * This is a MarkDoneCommand Class, which inherits from Command.
 * The execution of this command will mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "done";

    /**
     * Constructor for MarkDoneCommand.
     * @param userInput The input string entered by the user.
     * @throws DukeException
     */
    public MarkDoneCommand(String userInput) throws DukeException {
        String intString = userInput.substring(KEYWORD.length()).trim();
        try {
            this.taskNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please provide a valid task number!");
        }
    }

    /**
     * Mark a task as done.
     * @param taskList The existing list where the task is.
     * @return The completion message after execution.
     * @throws DukeException
     */
    public String execute(TaskList taskList) throws DukeException {
        boolean isInputValid = this.taskNumber <= taskList.getNumOfTasks()
                && this.taskNumber > 0;
        if (!isInputValid) {
            throw new DukeException("OOPS!!! Please choose a valid task number!");
        }
        taskList.markAsDone(this.taskNumber);
        Task task = taskList.getTask(this.taskNumber);
        return "Nice! I've marked this task as done:\n\t"
                + task.toString();
    }
}
