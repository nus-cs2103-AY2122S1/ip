package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents The Event task class
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * The constructor for Event task
     * @param description
     * @param at
     * @param isDone
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * the markDone method for marking the Event task as done
     * @return Event object
     */
    @Override
    public Event markDone() {
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
        return "E | " + mark + " | " + this.description +" | " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Executes input delete task
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
     * Overridden toString method to print Event task details
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
