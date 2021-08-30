package duke.command;

import duke.FileController;
import duke.UI;
import duke.tasks.TaskList;

public class PrintListCommand extends Command {
    public void execute(TaskList tasks, UI ui, FileController fc) {
        ui.printText(tasks.toString());
    }
}
