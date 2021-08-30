package duke.command;

import duke.exception.DukeException;
import duke.util.Tasklist;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates the list command inputted by the user
 *
 * @author Keith Tan
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    private Tasklist list;

    /**
     * Constructor for ListCommand.
     * Command that informs ui to print out the current task list
     */
    public ListCommand(Tasklist list) {

        this.list = list;

    }

    /**
     * Executes the list command. Prints the current task list for the user.
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {

        String listString = "Here are the tasks in your list:\n" + list.toString();
        return listString;

    }
}
