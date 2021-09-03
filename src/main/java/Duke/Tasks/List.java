package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui.Ui;

/**
 * Represents List task class
 */
public class List extends Task {

    /**
     * The constructor for List task
     */
    public List() {
        super("list", false);
    }

    /**
     * Executes input list task
     * @param task
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.showListDetails(task);
    }
}
