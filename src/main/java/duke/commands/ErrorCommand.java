package duke.commands;

import duke.errors.DukeError;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ErrorCommand extends Command {

    private final int code;

    public ErrorCommand(int code) {
        super(CommandType.ERROR, false);
        this.code = code;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        DukeError e = DukeError.getError(this.code);
        ui.showError(e);
    }
}
