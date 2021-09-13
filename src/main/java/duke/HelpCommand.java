package duke;

public class HelpCommand extends Command {

    /**
     * Executes <code>HelpCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Commands:\n"
                + "1. list: Lists all current tasks\n"
                + "2. todo *message*: Adds a Todo task with corresponding *message*\n"
                + "3. deadline *message* /by *date* (yyyy-mm-dd): "
                + "Adds a Deadline task with corresponding *message* and *date*\n"
                + "4. event *message* /at *date* (yyyy-mm-dd): "
                + "Adds an Event task with corresponding *message* and *date*\n"
                + "5. done *number*: Marks the task at *number* as done\n"
                + "6. delete *number*: Deletes the task at *number*\n"
                + "7. get *date* (yyyy-mm-dd): Finds all tasks falling on *date*\n"
                + "8. find *phrase*: Finds all tasks with *phrase* in the body";
        return message;
    }
}
