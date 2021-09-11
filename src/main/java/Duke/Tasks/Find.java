package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the Find class
 */
public class Find extends Task {

    protected String description;

    /**
     * Constructs Find class
     */
    public Find(String description) {
        super("find", false);
        this.description = description;
    }

    /**
     * Executes the input find task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String of find task details
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFindDetails(tasks, this.description);
    }

}
