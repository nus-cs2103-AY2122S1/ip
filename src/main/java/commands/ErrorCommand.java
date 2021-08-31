package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ErrorCommand implements Command {

    String msg;

    public ErrorCommand(String message) {
        this.msg = message;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return msg;
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
