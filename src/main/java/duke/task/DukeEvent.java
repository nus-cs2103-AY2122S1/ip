package duke.task;

public class DukeEvent extends DukeTask {
    private final DukeDate date;

    /**
     * Creates an event with a name and a date. The event is marked as not done.
     * @param name The name of the event
     * @param date The date of the event
     */
    public DukeEvent(String name, String date) {
        super(name);
        this.date = DukeDate.of(date);
    }

    /**
     * Creates an event with a name, date and whether or not it is marked as done.
     * @param name THe name of the event
     * @param isDone If the event is marked as done
     * @param date The date of the event
     */
    public DukeEvent(String name, boolean isDone, String date) {
        super(name, isDone);
        this.date = DukeDate.of(date);
    }

    /**
     * Returns the date of the event.
     * @return the date of the event
     */
    public DukeDate getDate() {
        return date;
    }

    @Override
    public String toCliString() {
        return String.format("%s (at %s)", super.toCliString(), date);
    }

    @Override
    public String toSerializedString() {
        return String.format("%s/%d/at/%s", getName(), isDone() ? 1 : 0, date);
    }
}
