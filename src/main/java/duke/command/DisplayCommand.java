package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.task.Storage;

public class DisplayCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayList();
    }

    public boolean isExit() {
        return false;
    }
}
