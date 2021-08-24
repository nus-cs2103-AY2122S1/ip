package duke;

/**
 * Command that lists all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listAllTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }
}
