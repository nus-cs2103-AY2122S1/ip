package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ListCommand implements Command {
    private String[] inputs;

    public ListCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /** Display all the tasks */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (inputs.length == 1) {
            return getTasks(taskList);
        } else {
            return "Wrong input format";
        }
    }

    /**
     * Displays all the tasks in the taskList.
     * @param taskList the TaskList to be printed
     */
    public String getTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.size() == 0) {
            return "There is no task yet.";
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(String.format("     %d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return s.toString();
    }
}
