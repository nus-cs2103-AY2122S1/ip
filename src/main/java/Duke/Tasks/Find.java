package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

/**
 * Represents the Find class
 */
public class Find extends Task {

    protected String description;
    /**
     * The constructor for Find task
     */
    public Find(String description) {
        super("find", false);
        this.description = description;
    }

    /**
     * Executes the input find task
     * @param task
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showFindDetails(task, this.description);
    }

}
