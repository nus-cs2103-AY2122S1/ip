package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodBye();
    }
}
