package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;


public class DeleteCommand implements Command {
    private String[] inputs;

    public DeleteCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /** Deletes the task */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (inputs.length != 2) {
            return "Wrong input format";
        }
        String index = inputs[1];
        return deleteTask(index, taskList, storage);
    }

    /**
     * Deletes target task from taskList and returns the message.
     * @param index the index of task to be deleted
     * @param taskList the taskList that stores all the tasks
     * @return the information of the deleted task and if error, the error message.
     */
    String deleteTask(String index, TaskList taskList, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        int idx;
        try {
            idx = Integer.parseInt(index);
            tasks.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "I'm sorry, but I don't know what that means. Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }

        Task currTask = tasks.get(idx - 1);
        tasks.remove(currTask);

        try {
            storage.saveData(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }

        String output = "     Noted. I've removed this task: \n"
                + "      " + currTask.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list. \n";
        return output;
    }
}
