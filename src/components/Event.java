package components;

import exceptions.DukeEmptyStringException;

public class Event extends Task {

    private String eventAt;

    private Event(String taskDescription, boolean done, String eventAt) {
        super(taskDescription, done);
        this.eventAt = eventAt;
    }

    public static Event of(String taskDescription, boolean done, String eventAt) throws DukeEmptyStringException {
        if (taskDescription.length() == 0) {
            throw new DukeEmptyStringException("Event description");
        }
        if (eventAt.length() == 0) {
            throw new DukeEmptyStringException("Event date");
        }
        return new Event(taskDescription, done, eventAt);
    }

    @Override
    public Event markDone() {
        return new Event(this.taskDescription, true, this.eventAt);
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[E] [X] %s (at %s)", this.taskDescription, this.eventAt);
        } else {
            return String.format("[E] [ ] %s (at %s)", this.taskDescription, this.eventAt);
        }
    }
}
