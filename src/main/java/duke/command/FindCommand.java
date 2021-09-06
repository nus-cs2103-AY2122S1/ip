package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.util.ArrayList;

public class FindCommand implements Command {
    private String[] inputs;

    public FindCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (inputs.length == 2) {
            String content = inputs[1];
            return findContent(content, taskList);
        } else {
            return "Wrong input format";
        }
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
        } else {
            return output;
        }
    }

}
