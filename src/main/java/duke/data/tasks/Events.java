package duke.data.tasks;

/**
 * Represents an Event, a Task with a date of type String.
 */
public class Events extends Task {
    private final String date;

    /**
     * Constructs an Event task.
     * @param name The description of the event
     * @param date The date which the event falls on
     */
    public Events(String name, String date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructs an Event task with the given completion status.
     * @param completed Whether the event is completed
     * @param name The description of the eventhe
     * @param deadline The date which the event falls on
     */
    public Events(boolean completed, String name, String deadline, String tags) {
        super(completed, name, tags);
        this.date = deadline;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("E~1~%s~%s~%s", this.getName(), this.date, this.getTags());
        } else {
            return String.format("E~0~%s~%s~%s", this.getName(), this.date, this.getTags());
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Events)) return false;

        Events event = (Events) o;

        return this.getName().equals(event.getName())
                && this.isCompleted() == event.isCompleted()
                && this.date.equals(event.getDate());
    }
}
