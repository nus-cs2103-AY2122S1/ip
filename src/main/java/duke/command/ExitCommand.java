package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

}
