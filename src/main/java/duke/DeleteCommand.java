package duke;

/**
 * Command to delete a task from existing list of tasks.
 */
public class DeleteCommand extends Command {

    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            return "Invalid value!";
        }
        Task taskRef = tasks.get(index);
        tasks.remove(index);
        String toReturn = ui.printDeleteMessage(taskRef);
        toReturn += ui.listTaskNumber(tasks);
        stats.incrementTaskDeleted();
        return toReturn;
        

    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
