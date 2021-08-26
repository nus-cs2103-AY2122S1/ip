package duke;

/**
 * A class to encapsulate the command of
 * marking a task as done.
 */
public class DoneCommand extends Command {

    /** The index of the task in the TaskList */
    private int taskNum;

    /**
     * A public constructor which initializes the command
     * and task number to the given one.
     *
     * @param command The command inputted by the user.
     * @param taskNum The index of the task.
     */
    public DoneCommand(String command, int taskNum) {
        super(command);
        this.taskNum = taskNum;
    }

    /**
     * Executes the done command inputted by the user,
     * by marking the specified task as done.
     *
     * @param tasks   The list of tasks stored so far.
     * @param ui      The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task temp = tasks.get(taskNum - 1);
        temp.markAsDone();
        ui.showLine();
        ui.doneTask(temp);
        ui.showLine();
        storage.editFileAll(tasks);
    }
}
