package nyx.command;

import java.io.IOException;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Task;
import nyx.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a DeleteCommand object.
     *
     * @param information Information needed to delete a task.
     */
    public DeleteCommand(String information) {
        super(information);
    }

    /**
     * Perform operations needed to delete a task.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return String representation of the message for the user.
     * @throws NyxException If an error is encountered while deleting the task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        try {
            int index = Integer.parseInt(information) - 1;

            if (taskList.getNumTasks() > 0) {
                Task task = taskList.getTask(index);
                taskList.removeTask(index);
                storage.overwriteData(taskList);
                return String.format("Noted! I've removed this task:\n  %s\nNow you have %d tasks in the list",
                        task, taskList.getNumTasks());
            } else {
                return "No task to delete!";
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new NyxException("Invalid task index!");
        } catch (IOException e) {
            throw new NyxException("Unable to save the changes...");
        }
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("Please specify the index to delete!");
    }
}
