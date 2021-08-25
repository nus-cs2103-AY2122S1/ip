package tasks;

public class Event extends Task {

    String time;

    public Event(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.time = time;
    }

    /**
     * Provides the String representation of the task in the format
     * meant for writing to the file.
     *
     * @return The string representation of this deadline for the file to be saved to.
     */
    @Override
    public String saveText() {
        int isDone = this.isDone ? 1 : 0;
        return "E | " + isDone + " | " + desc + " | " + time + "\n";
    }

    @Override
    public String toString() {
       return "[E]" + this.completionStatus() + desc + " (at: " + time + ")";
    }
}
