package winston;

import java.time.LocalDate;

public class Event extends Task {
    private final String type;
    private final LocalDate dateAndTime;
    
    /**
     * Constructor for Event
     *
     * @param description is the string of the description of the given task
     */
    public Event(String description, String dateAndTime, boolean isCompleted) {
        super(description, isCompleted);
        this.dateAndTime = DateHandler.formatDate(dateAndTime);
        this.type = "E";
    }

    public Event(String description, String dateAndTime) {
        super(description, false);
        this.type = "E";
        this.dateAndTime = DateHandler.formatDate(dateAndTime);
    }

    @Override
    public String saveFormat() {
        return this.type + "," + super.saveFormat() + "," + this.dateAndTime;

    }
    
    @Override
    public String toString() {
        return "[" + this.type + "] " + super.toString() + " (On: " + DateHandler.convertDate(this.dateAndTime) + ")";
    }
}