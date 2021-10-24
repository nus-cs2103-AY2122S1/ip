package duke;

/**
 * Command to set a task as completed.
 */
public class DoneCommand extends Command {

    private int index;

    DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            return "Invalid value!";
        }
        Task taskRef = tasks.get(index);
        taskRef.setDone();
        String toReturn = ui.printDoneTask(taskRef);
        stats.incrementTaskDone();
        return toReturn;
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
