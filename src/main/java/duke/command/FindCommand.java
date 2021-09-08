package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand implements Command {
    private String[] inputs;

    public FindCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /** Find the task with certain content */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (inputs.length != 2) {
            return "Wrong input format";
        }

        String content = inputs[1];
        return findContent(content, taskList);
    }

    String findContent(String content, TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        String output = "";
        for (Task t: lst) {
            if (t.getContent().contains(content)) {
                output += (t.toString() + "\n");
            }
        }
        if (output.equals("")) {
            return "No matching tasks";
        }

        return output;
    }
}
