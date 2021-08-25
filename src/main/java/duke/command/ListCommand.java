package duke.command;

import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) {
        taskHandler.printTasks();
    }
}