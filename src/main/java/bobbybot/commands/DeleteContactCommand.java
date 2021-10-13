package bobbybot.commands;

import bobbybot.exceptions.BobbyException;
import bobbybot.person.Person;
import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class DeleteContactCommand extends Command {
    private final int contactNumToDelete;

    public DeleteContactCommand(int contactNumToDelete) {
        this.contactNumToDelete = contactNumToDelete;
    }
    /**
     * Executes command
     *
     * @param tasks    task list
     * @param ui       ui
     * @param storage  storage
     * @param contacts
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        if (contactNumToDelete < 1 || contactNumToDelete > contacts.size()) {
            this.response = "Invalid delete command! Contact number: " + contactNumToDelete + " does not exist\n"
                    + "Use [list_contact] to see available contacts!";
            return;
        }

        try {
            Person contactToDelete = contacts.getContact(contactNumToDelete - 1);
            contacts.deleteContact(contactNumToDelete);
            storage.save(contacts);
            this.response = "Noted. I've removed this contact: " + contactNumToDelete
                    + "\nNow you have " + contacts.size() + " contacts in the list.";
        } catch (BobbyException e) {
            System.out.println(e.getMessage());
        }
    }
}
