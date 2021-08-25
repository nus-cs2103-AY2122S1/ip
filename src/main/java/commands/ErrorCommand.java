package commands;

import duke.*;
import tasks.*;
import exceptions.*;

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
