package duke.command;

import duke.ContactsList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The CommandContacts class handles the command "contacts" that handles the command
 * to print out all contacts in the contact list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandContacts extends Command {
    public static final String KEYWORD = "contacts";

    public CommandContacts() {
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui, ContactsList cl) {
        cl.printAllContacts();
    }

}
