package Duke.Commands;

import Duke.Errors.DukeError;
import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

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
