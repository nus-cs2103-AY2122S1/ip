package duke.commands;

import duke.Ui;
import duke.storage.Storage;
import duke.tasks.ToDo;

/**
 * Class to handle the todo command.
 */
public class ToDoCommand extends Command {
    ToDo toDo;

    /**
     * Public constructor for the ToDoCommand class
     *
     * @param toDo The toDo task.
     * @return A new ToDoCommand instance with the specified toDo task stored.
     */
    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    /**
     * Add the task to the list and print out reply message
     *
     * @param ui The Ui instance for printing
     * @param storage The Storage instance to add the task to
     * @return A boolean of false to indicate the main while loop should not be broken
     */
    @Override
    public boolean execute(Ui ui, Storage storage) {
        storage.addToList(toDo);
        ui.print("Got it! I've added this task to the list: \n"
                + "  " + toDo.toString() + '\n'
                + String.format("Now you have %d tasks in the list", storage.getSize()));
        storage.save();
        return false;
    }
}
