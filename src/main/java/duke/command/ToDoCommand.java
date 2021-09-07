package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.data.task.ToDo;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class that encapsulates the "ToDo" Command.
 *
 * @author Won Ye Ji
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "TODO";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds a todo task to the list";

    /**
     * Constructor for ToDoCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the tasklist.
     * @param arc Archive that holds the task information.
     */
    public ToDoCommand(TaskHandler th, Storage str, Storage arc) {
        super(th, str, arc);
    }

    /**
     * Executes the "ToDo" Command.
     *
     * @param cmd Command string to be executed.
     * @return Duke's response to the user.
     * @throws DukeException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        int minCommandLength = 6;
        if (cmd.length() < minCommandLength) {
            throw new DukeException(Ui.emptyDescription("todo"));
        } else {
            ToDo toDo = new ToDo(cmd.substring(5));
            String toPrint = taskHandler.addTask(toDo);
            toPrint = toPrint.concat(taskHandler.printNoOfTasks());
            storage.updateFile(taskHandler.formatTasksToSave("storage"));
            return toPrint;
        }
    }
}
