package duke;

/**
 * Command to add an Event Task to the existing list of tasks.
 */
public class EventCommand extends Command {

    private Task task;

    EventCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        tasks.add(task);
        String toReturn = ui.addedTaskMessage();
        toReturn += ui.printTask(task);
        toReturn += ui.listTaskNumber(tasks);
        stats.incrementTaskAdded();
        return toReturn;

    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
