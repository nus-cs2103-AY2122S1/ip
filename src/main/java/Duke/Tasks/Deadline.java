package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline class
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Thw contructor for Deadline Task
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * the markDone method for marking the task as done
     * @return Deadline task
     */
    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    /**
     * The formatChange method for change the done x -> 1 in Writing record tasks.txt version
     * @return String
     */
    @Override
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "D | " + mark + " | " + this.description + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * The execute version to process given deadline task
     * @param task
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.add(this);
        ui.showAddOnTask(task, (task.size() - 1));
        storage.writeData(task.getTasks());
    }

    /**
     * Overridden toString method to print deadline task details
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
