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

    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(toDo);
        storage.appendToFile(toDo);
        ui.printWithLines("Got it. I've added this task:\n  " + toDo
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
