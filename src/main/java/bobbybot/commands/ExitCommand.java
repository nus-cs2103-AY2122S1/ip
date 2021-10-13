package bobbybot.commands;

import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class ExitCommand extends Command {
    /**
     * Executes command
     * @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @param contacts
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        response = "Bye. Hope to see you again soon!";
    }

    /**
     * Prepares bot to exit
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
