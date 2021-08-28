package duke.command;

import duke.exception.DukeException;
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
     * @throws DukeException If arguments are invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be left empty. "
                    + "Please try again.", this.getCommand()));
        }

        Todo newTask = new Todo(arguments);
        tasks.add(newTask);
        ui.printToUser("Got it. I've added this task:");
        ui.printToUser("  " + newTask);
        ui.printToUser("Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.");
    }

    /**
     * Checks whether command terminate the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
