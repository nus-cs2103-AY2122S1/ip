package eightbit.command;

import eightbit.task.ToDo;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

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
     * @param ui Ui responsible for printing messages.
     * @param storage Storage responsible for reading/writing the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(toDo);
        storage.appendToFile(toDo);
        ui.printWithLines("Got it. I've added this task:\n  " + toDo
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
