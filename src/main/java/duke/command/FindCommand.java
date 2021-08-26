package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Finds a task by its description.
 */
public class FindCommand extends Command {
    private TaskList results;
    private final String regex;

    /**
     * Class constructor.
     *
     * @param regex Regex to match to.
     */
    public FindCommand(String regex) {
        this.regex = regex;
    }
    /**
     * Finds the task.
     * @param tasks TaskList to be searched.
     * @param ui Ui to display the results in.
     * @param storage Storage to interact with, not necessary.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        results = tasks.findTasks(this.regex);
        ui.showMessage(this.message(results));
    }

    /**
     * Returns the text representation of the found results.
     * @param tasks TaskList to be displayed.
     * @return Text representation of the found results.
     */
    @Override
    public String message(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");
        for(int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(String.format("%d. %s\n", i + 1, tasks.getIndex(i).toString()));
        }
        return stringBuilder.toString();
    }
}