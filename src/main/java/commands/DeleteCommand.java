package commands;

import exceptions.MorganException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;

/**
 * This is an DeleteCommand Class, which inherits from Command.
 * The execution of this command will delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String KEYWORD = "delete";
    public static final String INPUT_FORMAT = String.format("\t%s [task number]", KEYWORD);
    private static final String INPUT_FORMAT_ERROR = String.format("Please "
            + "ensure your input is in the following format:\n" + INPUT_FORMAT);
    private static final String TASK_NUMBER_ERROR = String.format("Please "
            + "provide a valid task number.");
    private final int taskNumber;

    /**
     * Constructor for DeleteCommand
     * @param userInput The input string entered by the user.
     * @throws MorganException If input format is invalid.
     */
    public DeleteCommand(String userInput) throws MorganException {
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
     * Delete a task from the task list.
     * @param tasks The existing list where a task will be deleted from.
     * @return The completion message after execution.
     */
    public String execute(TaskList tasks, Storage storage) throws MorganException {
        assert tasks != null && storage != null;
        boolean isValidTaskNumber = this.taskNumber <= tasks.getNumOfTasks()
                && this.taskNumber > 0;

        if (!isValidTaskNumber) {
            throw new MorganException(TASK_NUMBER_ERROR);
        }

        Task task = tasks.getTask(this.taskNumber);
        tasks.remove(this.taskNumber);
        storage.save(tasks);

        return "Noted. I've removed this task:\n\t" + task.toString()
                + "\nNow you have " + tasks.getNumOfTasks()
                + " tasks in the list.";
    }
}
