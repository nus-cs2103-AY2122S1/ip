package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
