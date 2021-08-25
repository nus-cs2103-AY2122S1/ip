package winston;

import java.time.LocalDate;

/**
 * A class extension of Task representing a deadline task
 */
public class Event extends Task {
    private final String type;
    private LocalDate dateAndTime;
    
    /**
     * Constructor for Event
     *
     * @param description is the string of the description of the given task
     */
    public Event(String description, String dateAndTime, boolean isCompleted) {
        super(description, "event", isCompleted);
        this.dateAndTime = DateHandler.readDate(dateAndTime);
        this.type = "E";
    }

    public Event(String description, String dateAndTime) {
        super(description, "event", false);
        this.type = "E";
        this.dateAndTime = DateHandler.readDate(dateAndTime);
    }

    /**
     * Method to convert information from object instance into a different format to be saved
     *
     * @return a string with the type, completion status, task description and due date
     */
    @Override
    public String saveFormat() {
        return this.type + "," + super.saveFormat() + "," + this.dateAndTime;

    }

    /**
     *  Method to convert information from object instance into a more readable format
     *
     * @return a string with the type, completion status, task description and due date
     */
    @Override
    public String toString() {
        return "[" + this.type + "] " + super.toString() + " (On: " + DateHandler.convertDate(this.dateAndTime) + ")";
    }
}