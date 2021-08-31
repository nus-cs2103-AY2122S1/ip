package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTaskList();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
