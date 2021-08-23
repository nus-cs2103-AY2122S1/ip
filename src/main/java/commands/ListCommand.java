package commands;

import duke.Storage;
import duke.Ui;
import tasktypes.TaskList;

public class ListCommand extends Command {

    public ListCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayList(taskList);
    }

}