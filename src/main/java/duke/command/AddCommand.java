package duke.command;

import duke.exception.DukeException;

import duke.util.Tasklist;
import duke.util.Ui;
import duke.util.Store;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-CodingStandard. Modify the code to comply with a given coding standard
 *
 * Description:
 * Class that encapsulates the add task command inputted by the user
 *
 * @author Keith Tan
 */
public class AddCommand extends Command {

    private final String taskDetails;
    private final String taskType;
    public static final String COMMAND_WORD = "add";


    public AddCommand(String taskDetails, String taskType) {

        this.taskDetails = taskDetails;
        this.taskType = taskType;

    }

    /**
     * Executes the add command and adds the inputted task to the task list
     *
     * @param list Tasklist of current tasks
     * @param ui Ui which prints any successful message from the associated methods
     * @param storage Current Storage of DUke
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {
        String successMessage = list.addTask(this.taskDetails, this.taskType);
        ui.printMessage(successMessage);

    }
}
