package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

public class UnintelligibleCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output(Responses.UNINTELLIGIBLE);
        ui.output(Responses.REQUIRED_FORMAT);
    }
}
