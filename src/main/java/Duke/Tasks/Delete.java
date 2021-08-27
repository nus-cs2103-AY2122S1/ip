package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.io.IOException;

/**
 * Represents the Delete task
 */
public class Delete extends Task {

    int num;

    /**
     * The constructor for Delete task
     * @param num
     */
    public Delete(int num) {
        super("delete", false);
        this.num = num;
    }

    /**
     * Executes input delete task
     * @param task
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Task taskDeleted = task.remove(num);
        ui.showDeletedMessage(task, taskDeleted);
        storage.writeData(task.getTasks());

    }
}
