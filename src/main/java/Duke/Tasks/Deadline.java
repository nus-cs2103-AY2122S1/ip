package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "D | " + mark + " | " + this.description + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public void excute(TaskList task, Ui ui, Storage storage) throws IOException {
        task.add(this);
        ui.showAddOnTask(task, (task.size() - 1));
        storage.writeData(task.getTasks());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
