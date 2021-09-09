package duke.task;

/**
 * @author Dr-Octavius
 *
 * Class that represents a Event Task.
 * Parent Class: Task
 *
 * @version 1.0
 */
public class Event extends Task {

    private final String at;

    /**
     * Class Constructor that takes 3 parameters
     *
     * @param description Deadline Task description
     * @param at Deatils of when Event will take place
     */
    public Event(String description, String at) {
        super(description,TASK_TYPE.E);
        this.at = at;
    }

    /**
     * Returns where the Task will take place
     *
     * @return String description of where Task will be done
     */
    public String getAt() {
        return at;
    }

    /**
     * String representation of a Deadline
     *
     * @return Summary of Event information
     */
    @Override
    public String toString() {
        return super.toString().concat(" (at: ".concat(this.at).concat(")"));
    }
}