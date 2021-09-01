package duke;

/**
 * Command to add a Deadline Task to the existing list of tasks.
 */
public class DeadlineCommand extends Command {

    private Task task;

    DeadlineCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        String toReturn = ui.printTask(task);
        toReturn += ui.listTaskNumber(tasks);
        return toReturn;
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
