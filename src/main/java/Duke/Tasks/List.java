package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

public class List extends Task {

    public List() {
        super("list", false);
    }

    @Override
    public void excute(TaskList task, Ui ui, Storage storage) {
        ui.showListDetails(task);
    }
}
