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
        ArrayList<Task> tasks = taskList.getTasks();

        String output = tasks
                .stream()
                .filter(task -> task.getContent().contains(content))
                .map(task -> task.toString())
                .reduce("", (previousTask, thisTask) -> previousTask + thisTask + "\n");

        if (output.equals("")) {
            return "No matched tasks";
        }

        return output;
    }
}
