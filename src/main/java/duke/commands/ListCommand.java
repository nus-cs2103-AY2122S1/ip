package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/** Represents command to list all values in the current taskList */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] messages = tasks.showList(ui);
        return String.join("\n", messages);
    }

    @Override
    public String toString() {
        return "TO PRINT: Enumerated tasklist";
    }
}
