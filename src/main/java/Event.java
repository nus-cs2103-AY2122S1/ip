/**
 * An Event class that inherits from Task and includes a time for the Task.
 */

public class Event extends Task {
    private String date;

    /**
     * Constructor.
     * @param title title of the task.
     * @param date time of the task.
     */
    public Event(String title, String date) {
        super(title);
        this.date = date;
    }

    /**
     * Returns basic info about the Event.
     * @return a String.
     */
    @Override
    public String getInfo() {
        return "[E][ ]" + this.getTitle() + "(at: " + this.date + ")";
    }

    /**
     * toString method.
     * @return a String.
     */
    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format("[E][ ]" + this.getTitle() + "(at: " + this.date + ")");
        } else {
            return String.format("[E][X]" + this.getTitle() + "(at: " + this.date + ")");
        }

    }


}
