package duke.Tasks;

import duke.Tool.Storage;
import duke.Tool.TaskList;
import duke.Ui.Ui;


/**
 * Represents the Delete task
 */
public class Delete extends Task {

    int num;

    /**
     * Constructs Delete class
     *
     * @param num
     */
    public Delete(int num) {
        super("delete", false);
        this.num = num;
    }

    /**
     * Executes input delete task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String delete task details
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDeleted = tasks.remove(num);
        storage.writeData(tasks.getTasks());
        return ui.showDeletedMessage(tasks, taskDeleted);
    }
}
