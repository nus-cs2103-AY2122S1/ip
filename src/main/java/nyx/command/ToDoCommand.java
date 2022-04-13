package nyx.command;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.TaskList;
import nyx.task.ToDo;

/**
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends Command {
    /**
     * Constructs a ToDoCommand object.
     *
     * @param information Information needed to add the ToDo task.
     */
    public ToDoCommand(String information) {
        super(information);
    }

    /**
     * Performs operations needed to add a ToDo task.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return String representation of the message for the user.
     * @throws NyxException If an error is encountered while adding the ToDo task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        ToDo toDo = new ToDo(information);
        return AddHandler.handleAdd(toDo, taskList, storage);
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("The description of a todo cannot be empty.");
    }
}
