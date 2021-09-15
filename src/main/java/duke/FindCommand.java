package duke;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) throws DukeException {
        if (query.equals("")) {
            throw new DukeException("Query should not be empty.");
        }
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showMatchingTasks(query, tasks);
        storage.addToHistory(tasks, this);
    }

    @Override
    public String toString() {
        return "Find tasks with query Command";
    }
}
