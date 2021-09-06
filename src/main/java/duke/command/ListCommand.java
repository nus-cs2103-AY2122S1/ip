package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ListCommand implements Command {
    private String[] inputs;

    public ListCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (inputs.length == 1) {
            return ui.printList(taskList);
        } else {
            return "Wrong input format";
        }
    }
}
