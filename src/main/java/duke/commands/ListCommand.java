package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;


/** Represents command to list all values in the current taskList */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showList(ui);
    }

    @Override
    public String toString() {
        return "TO PRINT: Enumerated tasklist";
    }
}
