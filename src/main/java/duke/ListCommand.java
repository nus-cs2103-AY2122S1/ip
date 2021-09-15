package duke;

/**
 * Represents list command that lists all tasks in task list.
 */
public class ListCommand extends Command {

    /**
     * Shows all tasks in UI.
     *
     * @param tasks Task list component.
     * @param storage Storage component.
     * @param ui UI component.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTaskList(tasks);
        storage.addToHistory(tasks, this);
    }

    @Override
    public String toString() {
        return "List all tasks Command";
    }
}
