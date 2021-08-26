package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.io.IOException;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "T | " + mark + " | " + this.description;
    }

    @Override
    public void excute(TaskList task, Ui ui, Storage storage) throws IOException {
        task.add(this);
        ui.showAddOnTask(task, (task.size() - 1));
        storage.writeData(task.getTasks());
    }

    @Override
    public String toString() {
        return "[T]"  + super.toString();
    }
}
