package duke.command;

import duke.Ui;
import duke.task.Storage;
import duke.task.TaskList;

public class DisplayCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayList();
    }

    public boolean isExit() {
        return false;
    }
}
