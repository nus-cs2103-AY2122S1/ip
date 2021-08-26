package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.io.IOException;

public class Delete extends Task {

    int num;

    public Delete(int num) {
        super("delete", false);
        this.num = num;
    }

    @Override
    public void excute(TaskList task, Ui ui, Storage storage) throws IOException {

        Task taskDeleted = task.remove(num);
        ui.showDeleteMessgae(task, taskDeleted);
        storage.writeData(task.getTasks());
    }
}
