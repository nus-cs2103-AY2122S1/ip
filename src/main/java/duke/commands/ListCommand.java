package duke.commands;

import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Represents command to list all values in the current taskList */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, CliUi cliUi, Storage storage) {
        String[] messages = tasks.showList(cliUi);
        return String.join("\n", messages);
    }

    @Override
    public String toString() {
        return "TO PRINT: Enumerated tasklist";
    }
}
