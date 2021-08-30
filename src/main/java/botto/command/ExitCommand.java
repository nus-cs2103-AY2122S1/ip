package botto.command;

import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

/**
 * Command for stopping the bot
 */
public class ExitCommand implements Command {

    /**
     * print goodBye message
     *
     * @param taskList the task list involved
     * @param ui the ui of the Botto bot
     * @param storage storage of the Botto bot
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayGoodBye();
    }

    /**
     * return true
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
