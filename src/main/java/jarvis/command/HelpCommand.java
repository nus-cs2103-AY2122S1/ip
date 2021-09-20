package jarvis.command;

import jarvis.message.OutputMessage;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

public class HelpCommand extends Command {
    /**
     * Shows the help text to the user.
     *
     * @param taskList The list in which the tasks are stored.
     * @param storage Storage to save or load tasks to hard-disk.
     * @param ui Ui to show information to the user.
     * @return A OutputMessage that needs to be shown to the user after execution.
     */
    @Override
    public OutputMessage execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.getHelpMessage();
    }
}
