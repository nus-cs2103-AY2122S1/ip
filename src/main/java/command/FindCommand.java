package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Represents a FindCommand.
 */
public class FindCommand extends Command {

    private String query;

    /**
     * Constructs a FindCommand object.
     *
     * @param query query term to find.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the FindCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return the result found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = tasks.find(query);
        String toReturn = ui.showResponse(result);

        return toReturn;
    }

    /**
     * Returns false indicating no program exit.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
