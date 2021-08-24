package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        // nothing here
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showList(ui);
    }

    @Override
    public String toString() {
        return "TO PRINT: Enumerated tasklist";
    }
}
