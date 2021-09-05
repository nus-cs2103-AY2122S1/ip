package duke.command;

import duke.ContactsList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.contact.Contact;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CommandAddContact class handles the command "addcontact" that adds a new contact
 * to the contact list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandDelContact extends Command {
    public static final String KEYWORD = "delcontact";
    private static final String ARG_FORMAT = "\\d|\\d\\d|100";
    private ArrayList<String> arguments;


    public CommandDelContact(ArrayList<String> arguments) {
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
        if (arguments.size() == 1) {
            Pattern pattern = Pattern.compile(ARG_FORMAT);
            Matcher matcher = pattern.matcher(arguments.get(0));
            return matcher.matches();
        } else {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui, ContactsList cl) {
        if (isValidArgument()) {
            int number = Integer.parseInt(arguments.get(0)) - 1;
            if (number + 1 <= cl.numberOfContacts() && number + 1 > 0) {
                cl.removeContact(number);
            } else {
                throw new DukeException("That contact does not exist!");
            }
        } else {
            throw new DukeException("Invalid argument for command: delcontact");
        }
    }
}
