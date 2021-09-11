package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui.Ui;

/**
 * Represents List task class
 */
public class List extends Task {

    /**
     * Constructs List class
     */
    public List() {
        super("list", false);
    }

    /**
     * Executes input list task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String of a list of task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListDetails(tasks);
    }
}
