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
     * Constructs Done class
     * @param num
     */
    public Done(int num) {
        super("done", true);
        this.num = num;
    }

    /**
     * Executes the input done task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String of deadline task details
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(num);
        storage.writeData(tasks.getTasks());
        return ui.showDoneMessage(tasks, num);
    }
}
