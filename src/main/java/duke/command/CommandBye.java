package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The CommandBye class handles the command "bye" that terminates the program.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandBye extends Command {
    public static final String KEYWORD = "bye";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        ui.printout(GOODBYE_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
