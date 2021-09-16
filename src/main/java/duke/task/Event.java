package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that start at a specific time
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructs Event object
     *
     * @param name name of task
     * @param at date and time for task to commence
     */
    public Event(String name, LocalDateTime at) {
        super(name);
        this.at = at;
    }

    /**
     Constructs Event object
     *
     * @param name name of task
     * @param at date and time task will be occurring at
     * @param isDone status of task
     */
    public Event(String name, LocalDateTime at, boolean isDone) {
        super(name, isDone);
        this.at = at;
    }


    /**
     * Stores task information in a specific format
     *
     * @return task information
     */
    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("E/%d/%s/%s", done, this.name, this.at.toString().replace("T", " "));
    }

    /**
     * Returns String of task information
     *
     * @return task information
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy K:mm a");
        return String.format("[E]%s(at:%s)", super.toString(), this.at.format(formatter));
    }
}
