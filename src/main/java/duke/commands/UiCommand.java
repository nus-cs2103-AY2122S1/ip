package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public class UiCommand extends Command {
    private final String uiMsg;

    public UiCommand(String uiMsg) {
        this.uiMsg = uiMsg;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printMessage(this.uiMsg);
    }

    @Override
    public boolean getIsExit() {
        return false;
    }
}
