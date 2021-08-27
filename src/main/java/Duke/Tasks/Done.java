package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.io.IOException;

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
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.markDone(num);
        ui.showDoneMessage(task, num);
        storage.writeData(task.getTasks());

    }
}
