package botto.command;

import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

public class ExitCommand implements Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
