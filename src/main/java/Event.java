public class Event extends Task {
    private String dateAndTime;
    private String type;
    /**
     * Constructor for Event
     *
     * @param description is the string of the description of the given task
     */
    public Event(String description, String dateAndTime, boolean isCompleted) {
        super(description, "event", isCompleted);
        this.dateAndTime = dateAndTime;
        this.type = "E";
    }

    public Event(String description, String dateAndTime) {
        super(description, "event", false);
        this.dateAndTime = dateAndTime;
        this.type = "E";
    }

    @Override
    public String saveFormat() {
        return this.type + "," + super.saveFormat() + "," + this.dateAndTime;

    }
    
    @Override
    public String toString() {
        return "[" + this.type + "] " + super.toString() + " (On: " + this.dateAndTime + ")";
    }
}