package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

public class ListCommand implements Command {

    private String input;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output(taskList.printList());
    }
}
