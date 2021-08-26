package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodBye();
        storage.saveData(taskList);
        return false;
    }
}
