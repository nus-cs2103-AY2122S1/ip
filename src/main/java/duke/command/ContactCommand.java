package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.data.Ui;
import duke.information.Contact;

/**
 * Command that adds a new Contact to Tasklist when executed.
 */
public class ContactCommand extends Command {
    /** Contact to be added to Tasklist. */
    private Contact contact;

    /**
     * Constructs ContactCommand class.
     *
     * @param contact Contact to be added to Tasklist.
     */
    public ContactCommand(Contact contact) {
        this.contact = contact;
    }

    /**
     * Adds a contact to the Tasklist.
     *
     * @param tasks The list of tasks that a user has.
     * @param ui The ui that sends a message to the user once the contact is added.
     * @param storage Saves the updated TaskList to disk.
     * @return The message produced by ui.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addContact(contact);
        storage.save(tasks);
        return ui.showAddedInformation();
    }
}