package bobbybot.commands;

import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class IncorrectCommand extends Command {

    /**
     * Executes command
     *  @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @param contacts
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        response = "I don't understand you! Please refer to the user guide for valid commands";
    }
}
