package duke.command;

import duke.*;
import duke.tasks.*;
import duke.exceptions.*;

public class PrintListCommand extends Command {
    public void execute(TaskList tasks, UI ui, FileController fc) {
        ui.printText(tasks.toString());
    }
}
