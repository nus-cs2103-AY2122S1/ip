package duke.command;

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
    private static final String HELP_MESSAGE = "List of Commands:\n"
            + "List,\n"
            + "Done,\n"
            + "Todo,\n"
            + "Deadline,\n"
            + "Event,\n"
            + "Delete,\n"
            + "Check,\n"
            + "Bye";


    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        ui.printout(HELP_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
