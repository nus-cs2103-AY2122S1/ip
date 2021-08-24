package duke;

public class ListCommand extends Command {
    /**
     * Executes the specific actions for this command.
     *
     * @param tasks Handles the list of tasks.
     * @param ui Handles the user interface.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList();
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            ui.printTaskInList(tasks.getTask(i), i);
        }
    }

    /**
     * Returns false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
