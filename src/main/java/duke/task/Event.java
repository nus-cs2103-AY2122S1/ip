package duke.task;

import duke.general.TaskType;

/**
 * duke.task.Event encapsulates the name of the event as well as the
 * when the event is occurring.
 */

public class Event extends Task {
    private String at;

    /**
     * Construct duke.task.Event object with a given name, and time of the event.
     * @param name The name of the event
     * @param at Time at which event is held
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String getInfo() {
        return this.at;
    }

    /**
     * Makes deep copy of itself
     * @return Event deep copy of itself
     */
    @Override
    public Task duplicate() {
        Task t = new Event(getName(), this.at);
        if (getCompletion().equals("X")) {
            t.toggleCompleted();
        }
        return t;
    }

    /**
     * Converts the event object into a string.
     * @return A string containing the type (event), completion status and time
     * at which event is held.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
