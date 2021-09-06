package duke.task;

import java.time.LocalDateTime;

class Event extends Task {
    /**
     * Date and time representation of event
     */
    private LocalDateTime at;

    /**
     * Constructor for an `Event` task.
     *
     * @param isDone      Indicates whether the task has been completed.
     * @param description Task description.
     * @param at          Date and time representation of event.
     */
    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = LocalDateTime.parse(at, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String saveAsData() {
        return 2 + "\n" + super.saveAsData() + "\n" + at.format(INPUT_FORMAT) + "\n";
    }
}
