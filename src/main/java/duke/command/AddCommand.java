package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class AddCommand implements Command {
    private String[] inputs;

    public AddCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {

    }

}
