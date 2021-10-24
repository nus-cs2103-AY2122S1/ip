package duke;

/**
 * Command to list existing tasks
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        return ui.listTasks(tasks);
    }

    @Override
    public boolean isClosed() {
        return false;
    }

}
