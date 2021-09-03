package bobbybot.commands;

import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class ExitCommand extends Command {
    /**
     * Executes command and get response
     * @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @return
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prepares bot to exit
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
