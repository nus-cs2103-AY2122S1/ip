package commands;
import exceptions.MorganException;
import tasks.Task;
import tasks.TaskList;

/**
 * This is a MarkDoneCommand Class, which inherits from Command.
 * The execution of this command will mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "done";
    private static final String INDEX_ERROR_MESSAGE = String.format("Please " +
            "provide a valid task number.");

    /**
     * Constructor for MarkDoneCommand.
     * @param userInput The input string entered by the user.
     * @throws MorganException
     */
    public MarkDoneCommand(String userInput) throws MorganException {
        String intString = userInput.substring(KEYWORD.length()).trim();

        // Parse user input to integer to obtain task number
        try {
            this.taskNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new MorganException(INDEX_ERROR_MESSAGE);
        }
    }

    /**
     * Mark a task as done.
     * @param taskList The existing list where the task is.
     * @return The completion message after execution.
     * @throws MorganException
     */
    public String execute(TaskList taskList) throws MorganException {
        // Checks if task number is valid
        boolean isInputValid = this.taskNumber <= taskList.getNumOfTasks()
                && this.taskNumber > 0;
        if (!isInputValid) {
            throw new MorganException(INDEX_ERROR_MESSAGE);
        }

        taskList.markAsDone(this.taskNumber);
        Task task = taskList.getTask(this.taskNumber);
        return "Nice! I've marked this task as done:\n\t"
                + task.toString();
    }
}
