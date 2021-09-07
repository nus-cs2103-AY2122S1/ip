package eightbit.command;

import eightbit.task.ToDo;
import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents a command to add a todo.
 */
public class ToDoCommand extends Command {

    private final ToDo toDo;

    /**
     * Constructs a command to add a todo.
     *
     * @param toDo ToDo instance to be added.
     */
    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    /**
     * Adds the todo into the user's list.
     *
     * @param taskList User's list of tasks.
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert toDo != null : "Todo should be initialized";

        taskList.add(toDo);
        storage.appendToFile(toDo);
        return "Got it. I've added this task:\n  " + toDo
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
