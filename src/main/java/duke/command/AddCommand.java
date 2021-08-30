package duke.command;

import duke.exception.DukeException;
import duke.util.Tasklist;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Description:
 * Class that encapsulates the add task command inputted by the user
 *
 * @author Keith Tan
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private final String taskDetails;
    private final String taskType;
    private Tasklist list;

    /**
     * Constructor for AddCommand.
     * Takes in the task details and type of task and creates a add command to add
     * the current task to the tasklist
     */
    public AddCommand(Tasklist list, String taskDetails, String taskType) {

        this.list = list;
        this.taskDetails = taskDetails;
        this.taskType = taskType;

    }

    /**
     * Executes the add command and adds the inputted task to the task list
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {
        return this.list.addTask(this.taskDetails, this.taskType);

    }
}
