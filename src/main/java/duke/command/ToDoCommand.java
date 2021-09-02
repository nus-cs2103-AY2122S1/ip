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
            + "Adds a todo task to the list";

    /**
     * Constructor for ToDoCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public ToDoCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "ToDo" Command.
     *
     * @param cmd Command string to be executed.
     */
    @Override
    public String execute(String cmd) throws DukeException {
        if (cmd.length() < 6) {
            throw new DukeException(Ui.emptyDescription("todo"));
        } else {
            ToDo toDo = new ToDo(cmd.substring(5));
            String toPrint = taskHandler.addToDo(toDo);
            toPrint = toPrint.concat(taskHandler.printNoOfTasks());
            storage.updateFile(taskHandler.formatTasksToSave());
            return toPrint;
        }
    }
}
