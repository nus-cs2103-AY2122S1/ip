package memocat.task;

import java.time.LocalDate;

import memocat.Parser;

/**
 * A Todo type task representation for Duke.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Creates an Event task.
     *
     * @param description Description of the event.
     * @param at LocalTime that the event will occur at.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + new Parser().formatLocalDate(at) + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return this.description.equals(otherEvent.description)
                    && (this.isDone == otherEvent.isDone) && this.at.equals(otherEvent.at);
        }
        return false;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Todo) {
            // always put todo task before other types
            // do not sort it for now
            return -1;
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return this.at.compareTo(eventTask.at);
        } else {
            assert task instanceof Deadline : "Invalid Task type: " + task;
            Deadline deadlineTask = (Deadline) task;
            return this.at.compareTo(deadlineTask.by);
        }
    }
}
