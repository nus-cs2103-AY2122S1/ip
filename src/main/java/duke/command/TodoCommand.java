package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Command to create Todo tasks.
 */
public class TodoCommand extends Command {
    private String taskDesc;

    /**
     * Constructor for TodoCommand.
     *
     * @param input User input.
     */
    public TodoCommand(String input) {
        this.taskDesc = input.replaceFirst("^todo", "");
    }

    /**
     * Creates a new Todo object and adds it to the current list.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If the user input is invalid.
     */
    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Todo tTask = new Todo(taskDesc);
        ls.addTask(tTask);
        storage.rewriteFile(ls);
    }

    /**
     * Signals to the system that the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
