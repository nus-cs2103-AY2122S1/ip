package duke.command;

import duke.ContactsList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.contact.Contact;

import java.util.ArrayList;

/**
 * The CommandAddContact class handles the command "addcontact" that adds a new contact
 * to the contact list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandAddContact extends Command {
    public static final String KEYWORD = "addcontact";
    private ArrayList<String> arguments;


    public CommandAddContact(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * This method checks if the description specified in the arguments
     * is in a valid format.
     *
     * @return A boolean indicating if the arguments are in a valid format.
     */
    @Override
    public boolean isValidArgument() {
        return arguments.size() == 4;
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui, ContactsList cl) {
        if (isValidArgument()) {
            Contact newContact = new Contact(arguments.get(0), arguments.get(1),
                    arguments.get(2), arguments.get(3));
            cl.addContact(newContact);
        } else {
            throw new DukeException("Invalid argument for Command: addcontact");
        }
    }
}
