package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        setExit();
        ui.exit();
    }
}
