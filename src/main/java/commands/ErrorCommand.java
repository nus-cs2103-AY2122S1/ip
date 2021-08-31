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
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        System.out.println(msg);
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
