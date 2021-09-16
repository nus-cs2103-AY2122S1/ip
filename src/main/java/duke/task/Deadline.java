package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date and time
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs Deadline object
     *
     * @param name name of task
     * @param by date and time for task to be completed by
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructs Deadline object
     *
     * @param name name of task
     * @param by date and time for task to be completed by
     * @param isDone status of task
     */
    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Stores task information in a specific format
     *
     * @return task information
     */
    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("D/%d/%s/%s", done, this.name, this.by.toString().replace("T", " "));
    }

    /**
     * Returns String of task information
     *
     * @return task information
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy K:mm a");
        return String.format("[D]%s(by:%s)", super.toString(), this.by.format(formatter));
    }

}
