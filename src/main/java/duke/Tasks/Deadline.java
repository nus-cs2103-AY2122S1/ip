package duke.Tasks;

import duke.Tool.Storage;
import duke.Tool.TaskList;
import duke.Ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline class
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs Deadline class
     *
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Marks down the deadline task
     *
     * @return Deadline task
     */
    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }

    /**
     * Changes the format let "X" -> 1 and record to tasks.txt version
     *
     * @return String changed format of deadline task in record
     */
    @Override
    public String changeFormat() {
        String mark = isDone ? "1" : "0";
        return "D | " + mark + " | " + this.description + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Executes input deadline task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String the details of deadline task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfBeforeTask = tasks.size();
        tasks.add(this);
        assert tasks.size() - numOfBeforeTask == 1 : "Add Deadline task not successful";
        storage.writeData(tasks.getTasks());
        return ui.showAddOnTask(tasks, tasks.size() - 1);
    }

    /**
     * Overrides toString method to print deadline task details
     *
     * @return String of deadline task details
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
