package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ByeCommand implements Command {
    /** prints goodbye message and exits the program */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
        return "bye";
    }
}
