package commands;
import exceptions.MorganException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;

/**
 * This is a MarkDoneCommand Class, which inherits from Command.
 * The execution of this command will mark a task as done.
 */
public class MarkDoneCommand extends Command {
    public static final String KEYWORD = "done";
    private static final String INPUT_FORMAT = String.format("\t\"%s [task number]\"", KEYWORD);
    private static final String INPUT_FORMAT_ERROR = String.format("Please "
            + "ensure your input is in the following format:\n" + INPUT_FORMAT);
    private static final String TASK_NUMBER_ERROR = String.format("Please "
            + "provide a valid task number.");
    private final int taskNumber;

    /**
     * Constructor for MarkDoneCommand.
     * @param userInput The input string entered by the user.
     * @throws MorganException If input format is invalid.
     */
    public MarkDoneCommand(String userInput) throws MorganException {
        String intString = userInput.substring(KEYWORD.length()).trim();

        // Checks if user specified task number
        if (intString.isEmpty()) {
            throw new MorganException(INPUT_FORMAT_ERROR);
        }

        // Checks if task number is an integer
        try {
            this.taskNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new MorganException(TASK_NUMBER_ERROR);
        }
    }

    /**
     * Mark a task as done.
     * @param taskList The existing list where the task is.
     * @return The completion message after execution.
     * @throws MorganException If input format is invalid.
     */
    public String execute(TaskList taskList, Storage storage) throws MorganException {
        boolean isValidTaskNumber = this.taskNumber <= taskList.getNumOfTasks()
                && this.taskNumber > 0;

        // Checks if task number is valid
        if (!isValidTaskNumber) {
            throw new MorganException(TASK_NUMBER_ERROR);
        }

        // Obtain task and mark done
        taskList.markAsDone(this.taskNumber);
        Task task = taskList.getTask(this.taskNumber);
        try {
            storage.save(taskList);
        } catch (MorganException e) {
            throw e;
        }

        // Message displayed upon execution
        return "Nice! I've marked this task as done:\n\t"
                + task.toString();
    }
}
