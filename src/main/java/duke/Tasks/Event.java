package duke.Tasks;

import duke.Tool.Storage;
import duke.Tool.TaskList;
import duke.Ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents The Event task class
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructs Event class
     *
     * @param description
     * @param at
     * @param isDone
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Marks down the event task
     *
     * @return Event the object of event task
     */
    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    /**
     * Changes the format let "X" -> 1 and record to tasks.txt version
     *
     * @return String changed format of event task in record
     */
    @Override
    public String changeFormat() {
        String mark = isDone ? "1" : "0";
        return "E | " + mark + " | " + this.description +" | " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Executes input delete task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String the details of event task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfBeforeExecute = tasks.size();
        tasks.add(this);
        assert tasks.size() - numOfBeforeExecute== 1 : "Add event task not successful";
        storage.writeData(tasks.getTasks());
        return ui.showAddOnTask(tasks, (tasks.size() - 1));
    }

    /**
     * Overrides toString method to print Event task details
     * @return String of event task details
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
