package Duke.command;

import Duke.exception.DukeException;

import Duke.util.Tasklist;
import Duke.util.Ui;
import Duke.util.Store;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-Jar. Create a JAR file
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
