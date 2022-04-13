package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * The Todo Command.
 */
public class TodoCommand extends Command {

    /**
     * The arguments associated with the command
     **/
    private String arguments;

    /**
     * Constructs the todo command.
     *
     * @param arguments The arguments associated with the command.
     */
    public TodoCommand(String arguments) {
        super("todo");
        this.arguments = arguments;
    }

    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     * @return The output of executing the command.
     * @throws DukeException If arguments are invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Parser.checkAddTaskArgument(this.getCommand(), this.arguments);
        Todo newTask = new Todo(arguments);
        tasks.add(newTask);
        return "Got it. I've added this task:\n" + "  "
                + newTask + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.";
    }

}
