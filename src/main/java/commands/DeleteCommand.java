package commands;
import exceptions.MorganException;
import tasks.Task;
import tasks.TaskList;

/**
 * This is an DeleteCommand Class, which inherits from Command.
 * The execution of this command will delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;
    public static final String KEYWORD = "delete";
    private static final String INPUT_FORMAT = String.format("\t\"%s [task number]\"", KEYWORD);
    private static final String INPUT_FORMAT_ERROR_MESSAGE = String.format("Please " +
            "ensure your input is in the following format:\n" + INPUT_FORMAT);
    private static final String TASK_NUMBER_ERROR_MESSAGE = String.format("Please " +
            "provide a valid task number.");

    /**
     * Constructor for DeleteCommand
     * @param userInput The input string entered by the user.
     * @throws MorganException
     */
    public DeleteCommand(String userInput) throws MorganException {
        String intString = userInput.substring(KEYWORD.length()).trim();

        // Checks if user specified task number
        if (intString.isEmpty()) {
            throw new MorganException(INPUT_FORMAT_ERROR_MESSAGE);
        }

        // Checks if task number is an integer
        try {
            this.taskNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new MorganException(TASK_NUMBER_ERROR_MESSAGE);
        }
    }

    /**
     * Delete a task from the task list.
     * @param taskList The existing list where a task will be deleted from.
     * @return The completion message after execution.
     */
    public String execute(TaskList taskList) throws MorganException {
        boolean isValidTaskNumber = this.taskNumber <= taskList.getNumOfTasks()
                && this.taskNumber > 0;

        // Checks if task number is valid
        if (!isValidTaskNumber) {
            throw new MorganException(TASK_NUMBER_ERROR_MESSAGE);
        }

        // Obtain task and remove
        Task task = taskList.getTask(this.taskNumber);
        taskList.remove(this.taskNumber);

        // Message displayed upon execution
        return "Noted. I've removed this task:\n\t" + task.toString()
                + "\nNow you have " + taskList.getNumOfTasks()
                + " tasks in the list.";
    }
}
