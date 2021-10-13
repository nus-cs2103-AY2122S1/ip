package bobbybot.commands;

import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class ListContactsCommand extends Command {

    /**
     * Executes command
     *  @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @param contacts
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        StringBuilder response = new StringBuilder();
        response.append("Here are your contacts:");

        for (int i = 0; i < contacts.size(); i++) {
            response.append("\n").append(i + 1).append(". ");
            response.append(contacts.getContact(i));
        }
        this.response = response.toString();
    }
}
