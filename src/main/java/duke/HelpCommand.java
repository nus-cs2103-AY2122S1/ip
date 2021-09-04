package duke;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Commands:\n"
                + "list: Lists all current tasks\n"
                + "todo *message*: Adds a Todo task with corresponding *message*\n"
                + "deadline *message* /by *date* (yyyy-mm-dd): "
                + "Adds a Deadline task with corresponding *message* and *date*\n"
                + "event *message* /at *date* (yyyy-mm-dd): "
                + "Adds an Event task with corresponding *message* and *date*\n"
                + "done *number*: Marks the task at *number* as done\n"
                + "delete *number*: Deletes the task at *number*\n"
                + "get *date* (yyyy-mm-dd): Finds all tasks falling on *date*\n"
                + "find *phrase*: Finds all tasks with *phrase* in the body";
        return message;
    }
}
