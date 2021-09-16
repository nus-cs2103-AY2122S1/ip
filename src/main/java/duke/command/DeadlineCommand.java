package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;


/**
 * Command to create Deadline tasks.
 */
public class DeadlineCommand extends Command {
    private String taskDesc = "";
    private String deadline = "";

    /**
     * Constructor for DeadlineCommand.
     *
     * @param input User's input.
     */
    public DeadlineCommand(String input) {
        this.taskDesc = input.replaceFirst("^deadline", "").split(" /")[0];
        assert input.contains("/by") : "missing /by command";
        this.deadline = input.substring(input.indexOf("/by") + 4);
    }

    /**
     * Creates a new Deadline object and adds it to the current list.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If Deadline object is invalid due to invalid input.
     */
    @Override
    public String execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Deadline dTask = new Deadline(taskDesc, deadline);
        ls.addTask(dTask);
        storage.rewriteFile(ls);
        return ui.addTaskToList(dTask, ls.getSize());
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
