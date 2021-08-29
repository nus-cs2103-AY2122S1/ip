package duke.command;

import duke.exception.DukeException;
import duke.util.Store;
import duke.util.Tasklist;
import duke.util.Ui;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates the mark task command inputted by the user
 *
 * @author Keith Tan
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private final int toMark;

    /**
     * Constructor for MarkCommand.
     * Takes in the task number to mark and creates a mark command
     */
    public MarkCommand(int toMark) {

        this.toMark = toMark;

    }

    /**
     * Executes the mark task command. Marks a specific task in the task list as completed.
     *
     * @param list Tasklist of current tasks
     * @param ui Ui which prints any successful message from the associated methods
     * @param storage Current Storage of DUke
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {
        String successMessage = list.markTask(toMark);
        ui.printMessage(successMessage);

    }

}
