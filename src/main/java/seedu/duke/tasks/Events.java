package seedu.duke.tasks;

public class Events extends Task {

    /**
     * Constructor. Default having the isDone parameter to be set as false.
     * 
     * @param description description of the current Events object.
     * @param dateTime    is the description of the location and time for the
     *                    Events.
     */
    public Events(String description, String dateTime) {
        super(description, dateTime);
    }

    /**
     * Second Constructor. Initialising the parameters of the Events.
     * 
     * @param description is the description of the Events.
     * @param dateTime    is the description of the location and time for the
     *                    Events.
     * @param isDone      determine whether the Events are completed or not.
     */
    public Events(String description, String dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    /**
     * Retrieves the symbol of the current object. Different object and child have
     * different symbols that represents them.
     * 
     * @return a fixed parent symbol "E".
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    /**
     * Marks the current Events as done.
     * 
     * @return a new Events object with the same description and dayTime, but
     *         setting isDone property to be true
     */
    @Override
    public Events markAsDone() {
        return new Events(this.getDescription(), this.getDateTime(), true);
    }

    /**
     * Describes the current Events object.
     * 
     * @return a description of the current Events object.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.getDateTime() + ")";
    }
}
