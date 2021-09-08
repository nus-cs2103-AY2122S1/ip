package tasks;

import exceptions.EmptyDescException;

public class Event extends Task {

    String time;

    public Event(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.time = time;
    }

    public Event(String desc, Boolean isDone, String[] tags, String time) throws EmptyDescException {
        super(desc, isDone, tags);
        this.time = time;
    }

    /**
     * Provides the String representation of the task in the format
     * meant for writing to the file.
     *
     * @return The string representation of this deadline for the file to be saved to.
     */
    @Override
    public String getSaveText() {
        int isDone = this.isDone ? 1 : 0;
        return "E | " + isDone + " | " + desc + " | " + time + " | " + this.getTagString() + "\n";
    }

    @Override
    public String toString() {
        String tagString = this.getTagString();
        return "[E]" + this.completionStatus() + desc + " (at: " + time + ")"
                + (tagString.equals("-") ? "" : " (Tags: " + tagString + ")");
    }
}
