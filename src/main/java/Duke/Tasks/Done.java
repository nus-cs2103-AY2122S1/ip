package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui.Ui;

/**
 * Represents the Done task class
 */
public class Done extends Task {

    int num;

    /**
     * The constructor for Done task
     * @param num
     */
    public Done(int num) {
        super("done", true);
        this.num = num;

    }

    /**
     * Executes the Done task
     * @param task
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        task.markDone(num);
        storage.writeData(task.getTasks());
        return ui.showDoneMessage(task, num);
    }
}
