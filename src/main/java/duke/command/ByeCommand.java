package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ByeCommand implements Command{
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
        System.exit(0);
    }
}
