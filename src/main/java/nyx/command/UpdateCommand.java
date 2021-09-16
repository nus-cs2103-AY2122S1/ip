package nyx.command;

import java.io.IOException;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Deadline;
import nyx.task.Event;
import nyx.task.Task;
import nyx.task.TaskList;
import nyx.task.ToDo;


/**
 * Represents a command to edit a specific task
 */
public class UpdateCommand extends Command {
    /**
     * Constructs an UpdateCommand object.
     *
     * @param information The information needed to edit the task.
     */
    public UpdateCommand(String information) {
        super(information);
    }

    /**
     * Performs operations needed to edit a specific task.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return String representation of the message for the user.
     * @throws NyxException If an error is encountered while editing the task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        String[] splitInfo = information.split(" ", 2);
        assert splitInfo.length > 0 : "Update information not read in correctly";

        try {
            int index = Integer.parseInt(splitInfo[0]) - 1;
            if (taskList.getNumTasks() == 0) {
                return "No task to delete!";
            }
            Task task = taskList.getTask(index);
            return updateHandler(splitInfo[1], task, taskList, storage);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new NyxException("Invalid task index!");
        }
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("You need to specify what to update!");
    }

    private String updateHandler(String newInfo, Task task, TaskList taskList, Storage storage) throws NyxException {
        if (task instanceof ToDo) {
            task.updateContent(newInfo);
        } else if (task instanceof Deadline) {
            updateDeadline(newInfo, task);
        } else {
            updateEvent(newInfo, task);
        }

        try {
            storage.overwriteData(taskList);
        } catch (IOException e) {
            throw new NyxException("Unable to save the changes...");
        }

        return String.format("Noted! I've update this task to:\n  %s", task);
    }

    private void updateDeadline(String newInfo, Task task) throws NyxException {
        String[] secondInfo = newInfo.split(" /by ");
        if (secondInfo.length != 2) {
            throw new NyxException("You need to update deadline using the following format:"
                    + "\n{ details } /by { datetime }");
        }
        task.updateContent(secondInfo[0]);
        Deadline deadline = (Deadline) task;
        deadline.changeDateTime(secondInfo[1]);
    }

    private void updateEvent(String newInfo, Task task) throws NyxException {
        String[] secondInfo = newInfo.split(" /at ");
        if (secondInfo.length != 2) {
            throw new NyxException("You need to update event using the following format:"
                    + "\n{ details } /at { datetime }");
        }
        task.updateContent(secondInfo[0]);
        Event event = (Event) task;
        event.changeDateTime(secondInfo[1]);
    }
}
