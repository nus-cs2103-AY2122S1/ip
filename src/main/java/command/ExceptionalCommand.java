package command;

import core.Storage;
import core.TaskList;
import gui.Ui;

public class ExceptionalCommand extends Command {
    private Exception e;

    public ExceptionalCommand(Exception e) {
        this.e = e;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        String message = e.getMessage();
        Ui.formatAndPrint(message);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
