package duke;

/**
 * Event class that extends from Task class
 */
public class Event extends Task {
    protected String at;

    /**
     *
     * @param description takes in a String describing the task description
     * @param at takes in a String representing the time of the task
     * @param number takes in a integer representing the task number
     */
    public Event(String description, String at,int number) {
        super(description,number);
        this.at = at;
    }

    /**
     *
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
