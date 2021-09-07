package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.util.ArrayList;

public class ListCommand implements Command {
    private String[] inputs;

    public ListCommand(String[] inputs) {
        this.inputs = inputs;
    }

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
        ArrayList<Task> lst = taskList.getTasks();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            s.append(String.format("     %d. %s\n", i + 1, lst.get(i).toString()));
        }
        return s.toString();
    }
}
