package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    public static final String INSTRUCTION_FIND = "find";
    protected String description;

    /**
     * Class constructor for FindCommand Class specifying the task number
     */
    public FindCommand(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("☹ OOPS!!! The task number of delete cannot be empty.");
        }
        this.description = description;
    }

    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = new TaskList(tasks.match(description));
        ui.printList(foundTasks);
    }

    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[" + INSTRUCTION_FIND + "] - " + description;
    }
}
