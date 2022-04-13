package duke.command;

import duke.data.Storage;
import duke.data.InformationList;
import duke.data.Ui;

/**
 * Command that displays a user's saved information when executed.
 */
public class ListCommand extends Command {
    /**
     * Displays a user's saved tasks and contacts.
     *
     * @param tasks   The list of tasks that a user has.
     * @param ui      The ui that sends InformationList as a string to the user.
     * @param storage Not used for this command.
     * @return The message produced by ui.
     */
    @Override
    public String execute(InformationList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks.searchTaskByKeyword("")) + ui.showContacts(tasks.searchContactByKeyword(""));
    }
}
