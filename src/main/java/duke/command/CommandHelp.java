package duke.command;

import duke.ContactsList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The CommandList class handles the command "help" that lists out all the
 * available commands in the program.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandHelp extends Command {

    public static final String KEYWORD = "help";
    private static final String HELP_MESSAGE = "I don't understand your command.\nList of Commands:\n"
            + "1. List,\n"
            + "2. Done,\n"
            + "3. Todo,\n"
            + "4. Deadline,\n"
            + "5. Event,\n"
            + "6. Delete,\n"
            + "7. Check,\n"
            + "8. Contacts,\n"
            + "9. AddContact,\n"
            + "10. DelContact,\n"
            + "11. Find.\n";


    @Override
    public void execute(TaskList tl, Storage st, Ui ui, ContactsList cl) {
        ui.addDialog(HELP_MESSAGE, true);
    }

}
