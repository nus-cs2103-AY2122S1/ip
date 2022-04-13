package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The delete command.
 */
public class DeleteCommand extends Command {

    /**
     * The arguments associated with the command.
     **/
    private String arguments;

    /**
     * Constructs the delete command.
     *
     * @param arguments The arguments associated with the command.
     */
    public DeleteCommand(String arguments) {
        super("delete");
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
        int index = Parser.parseIndex(arguments, tasks.size());
        Task taskDeleted = tasks.remove(index - 1);
        return "Noted! I've removed this task:\n" + "  "
                + taskDeleted + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.";
    }

}
