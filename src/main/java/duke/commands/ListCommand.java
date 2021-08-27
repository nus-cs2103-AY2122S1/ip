package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTaskList();
    };
}
