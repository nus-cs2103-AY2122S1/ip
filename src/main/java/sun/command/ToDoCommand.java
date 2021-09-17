package sun.command;

import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.data.task.ToDo;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that encapsulates the "ToDo" Command.
 *
 * @author Won Ye Ji
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "TODO";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": adds a todo task.\n*Format: todo <task description> (e.g todo homework)\n";

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
     * @return Sun's response to the user.
     * @throws SunException if task faces an error during execution.
     */
    @Override
    public String execute(String cmd) throws SunException {
        int minCommandLength = 6;
        if (cmd.length() < minCommandLength) {
            throw new SunException(Ui.getCommandFormatError("todo"));
        } else {
            ToDo toDo = new ToDo(cmd.substring(5));
            String toPrint = taskHandler.addTask(toDo);
            toPrint = toPrint.concat(taskHandler.printNoOfTasks());
            storage.updateFile(taskHandler.formatTasksToSave("storage"));
            return toPrint;
        }
    }
}
