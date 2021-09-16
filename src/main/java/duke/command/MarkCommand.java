package duke.command;

import duke.exception.DukeException;
import duke.util.Tasklist;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Description:
 * Class that encapsulates the mark task command inputted by the user
 *
 * @author Keith Tan
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private int toMark;
    private Tasklist list;

    /**
     * Constructor for MarkCommand.
     * Takes in the task number to mark and creates a mark command
     */
    public MarkCommand(Tasklist list, int toMark) {

        this.list = list;
        this.toMark = toMark;

    }

    /**
     * Executes the mark task command. Marks a specific task in the task list as completed.
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {
        return this.list.markTask(toMark);

    }

}
