package duke;

/**
 * Command to list existing tasks
 */
public class ListCommand extends Command {
    ListCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.listTasks(tasks);
    }

    @Override
    public boolean isClosed() {
        return false;
    }
    
}
