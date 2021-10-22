package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected String priority;
    protected LocalDate atDate;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected String[] monthArray = new  String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public Event(String description, String at, String priority) {
        super(description);
        this.at = at;
        this.atDate = LocalDate.parse(at, formatter);
        this.priority = priority;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return if the task is marked as done.
     */
    public boolean getIsDone() { return this.isDone;}

    /**
     * Retrieves the description of the task instance.
     *
     * @return the string description of the task
     */
    public String getDescription() { return this.description; }

    /**
     * Returns the date that the event is at
     *
     * @return a string of the date
     */
    public String getAt() {return this.at;}
    public String getPriority() {return this.priority;}

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + monthArray[atDate.getMonthValue() - 1] + " " + atDate.getDayOfMonth() + " " + atDate.getYear() + ")"+ " [Priority " + this.priority + "]";
    }
}
