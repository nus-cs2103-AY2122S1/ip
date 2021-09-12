package main.java.duke.commands;

import java.io.IOException;

import main.java.duke.DukeException;
import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.extensions.Contact;

/**
 * A command that adds a new contact to the contact list.
 */
public class AddContactCommand extends ContactCommand {
    private final Contact contact;

    /**
     * Constructs a new add contact command with the given contact.
     *
     * @param contact the given contact
     */
    public AddContactCommand(Contact contact) {
        this.contact = contact;
    }

    /**
     * Executes the add contact command.
     *
     * @param contacts given list of contacts
     * @param gui given gui object
     * @param storage given storage object
     * @throws IOException input and output exception
     * @throws DukeException duke exception
     */
    public String execute(TaskList contacts, MainWindow gui, Storage storage) throws IOException, DukeException {
        storage.saveTaskToFile(contact.toString());
        return addContact(contact, contacts);
    }

    private String addContact(Contact contact, TaskList contactList) {
        String message = ("Got it. I've added this contact: \n" + contact.toString());
        contactList.getTaskList().add(contact);
        String taskForm;

        if (contactList.getTaskList().size() == 1) {
            taskForm = " task";
        } else {
            taskForm = " tasks";
        }
        message += ("Now you have " + (contactList.getTaskList().size()) + taskForm + " in the list.");
        return message;
    }
}
