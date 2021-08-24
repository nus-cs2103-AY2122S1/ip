package commands;
import tasks.Task;
import tasks.TaskList;
import exceptions.DukeException;

/**
 * This is an DeleteCommand Class, which inherits from Command.
 * The execution of this command will delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "delete";

    /**
     * Constructor for DeleteCommand
     * @param userInput The input string entered by the user.
     * @throws DukeException
     */
    public DeleteCommand(String userInput) throws DukeException {
        String intString = userInput.substring(KEYWORD.length()).trim();
        try {
            this.taskNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please provide a valid task number!");
        }
    }

    /**
     * Delete a task from the task list.
     * @param taskList The existing list where a task will be deleted from.
     * @return The completion message after execution.
     */
    public String execute(TaskList taskList) throws DukeException {
        boolean isInputValid = this.taskNumber <= taskList.getNumOfTasks()
                && this.taskNumber > 0;
        if (!isInputValid) {
            throw new DukeException("OOPS!!! Please choose a valid task number!");
        }
        Task task = taskList.getTask(this.taskNumber);
        taskList.remove(this.taskNumber);
        return "Noted. I've removed this task:\n\t" + task.toString()
                + "\nNow you have " + taskList.getNumOfTasks()
                + " tasks in the list.";
    }
}
