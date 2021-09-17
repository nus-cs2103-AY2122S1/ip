package duke.task;

/**
 * Represents the Events that are added to TaskList. Extends Task.
 */
public class Event extends Task{
    private boolean isDone;
    private String taskString;
    private String timeSlot;

    public Event(String taskString, String timeSlot) {
        this.taskString = taskString;
        this.isDone = false;
        this.timeSlot = timeSlot;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskString() {
        if(isDone) {
            return "[E][X] " + this.taskString
                    + " (at: " + this.timeSlot + ")";
        } else {
            return "[E][ ] " + this.taskString
                    + " (at: " + this.timeSlot + ")";
        }
    }
}

