package bobbybot.commands;

import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class IncorrectCommand extends Command {

    /**
     * Executes command
     *
     * @param tasks   task list
     * @param ui      ui
     * @param storage storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        response = "I don't understand you! Please refer to the user guide for valid commands";
    }
}
