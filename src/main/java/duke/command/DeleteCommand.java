package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.util.ArrayList;

public class DeleteCommand implements Command {
    private String[] inputs;

    public DeleteCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (infos.length == 2) {
            String index = infos[1];
            return deleteTask(index, taskList);
        } else {
            return "Wrong input format";
        }
    }

    /**
     * Deletes target task from taskList and returns the message.
     * @param index the index of task to be deleted
     * @param taskList the taskList that stores all the tasks
     * @return the information of the deleted task and if error, the error message.
     */
    String deleteTask(String index, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int idx;
        try {
            idx = Integer.parseInt(index);
            tasks.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }

        Task currTask = tasks.get(idx - 1);
        tasks.remove(currTask);

        String output = "    ____________________________________________________________\n"
                + "     Noted. I've removed this task: \n"
                + "      " + currTask.toString() + "\n"
                + "     Now you have " + tasks.size() + " tasks in the list. \n"
                + "    ____________________________________________________________\n";
        return output;
    }
}
