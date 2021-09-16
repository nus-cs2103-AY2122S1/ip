package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.task.Task;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input);
    }

    /**
     * The method that executes the command
     *
     * @param tasks list of the tasks
     * @param ui the ui object responsible for user interaction
     * @param storage the storage object responsible for writing and reading txt file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }

    @Override
    protected Task processTask(TaskList tasks) {
        return null;
    }

    /**
     * The method that checks if the bot should be stopped.
     *
     * @return the boolean true to stop the bot
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
