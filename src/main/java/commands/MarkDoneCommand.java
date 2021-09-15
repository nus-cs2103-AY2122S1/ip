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
    public static final String INPUT_FORMAT = String.format("\t%s [task number]", KEYWORD);
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
        assert userInput != null;
        String intString = userInput.substring(KEYWORD.length()).trim();

        if (intString.isEmpty()) {
            throw new MorganException(INPUT_FORMAT_ERROR);
        }

        try {
            this.taskNumber = Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new MorganException(TASK_NUMBER_ERROR);
        }
    }

    /**
     * Mark a task as done.
     * @param tasks The existing list where the task is.
     * @param storage The storage object to store task data.
     * @return The completion message after execution.
     * @throws MorganException If input format is invalid.
     */
    public String execute(TaskList tasks, Storage storage) throws MorganException {
        assert tasks != null && storage != null;
        boolean isValidTaskNumber = this.taskNumber <= tasks.getNumOfTasks()
                && this.taskNumber > 0;
        if (!isValidTaskNumber) {
            throw new MorganException(TASK_NUMBER_ERROR);
        }

        tasks.markAsDone(this.taskNumber);
        Task task = tasks.getTask(this.taskNumber);
        storage.save(tasks);

        return "Nice! I've marked this task as done:\n\t"
                + task.toString();
    }
}
