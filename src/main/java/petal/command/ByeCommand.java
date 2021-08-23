package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.InvalidInputException;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveTasks();
        } catch (InvalidInputException e) {
            ui.output(e.getMessage());
        }
    }

}
