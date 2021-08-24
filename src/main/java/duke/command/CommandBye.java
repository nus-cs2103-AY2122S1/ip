package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class CommandBye extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye("Alex");
        this.isExit = true;
    }
}
