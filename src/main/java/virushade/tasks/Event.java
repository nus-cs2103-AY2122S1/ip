package virushade.tasks;

import virushade.StringManipulator;

public class Event extends Task {
    private final String time;

    /**
     * The constructor for an Events Task.
     * @param taskDescription The name of the task.
     * @param time The time of the task.
     */
    public Event(String taskDescription, String time, boolean isDone) {
        super(taskDescription, isDone);
        this.time = StringManipulator.convertDateAndTime(time);
    }

    /**
     * @return String representation of Events task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " /at " + time;
    }
}
