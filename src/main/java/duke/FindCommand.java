package duke;

import java.util.List;

/**
 * Command to find tasks by searching for a keyword.
 *
 * @author felix-ong
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the specific actions for this command.
     *
     * @param tasks   Handles the list of tasks.
     * @param ui      Handles the user interface.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showFound();
        List<Task> matches = tasks.findTask(this.keyword);
        for (int i = 0; i < matches.size(); i++) {
            ui.printTaskInList(matches.get(i), i);
        }
    }

    /**
     * Returns true if the command calls for the program to exit,
     * false otherwise.
     *
     * @return true if command calls for the program to exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
