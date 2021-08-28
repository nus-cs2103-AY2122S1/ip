package duke;

/**
 * Event class that extends from Task class
 */
public class Event extends Task {
    protected String at;

    /**
     * creates an Event object
     * @param description takes in a String describing the task description
     * @param at takes in a String representing the time of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * gets event timing
     * @return getter method to get event time
     */
    public String getAt(){
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")" ;
    }
}
