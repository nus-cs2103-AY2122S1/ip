package duke;

/**
 * Deadline class that extends from Task class
 */
public class Deadline extends Task {

    protected String by;

    /**
     *
     * @param description takes in a String describing the task description
     * @param by takes in a String representing the deadline of the task
     */
    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }

    /**
     * gets task deadline
     * @return a String representing the deadline
     */
    public String getBy(){
        return this.by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")" ;
    }
}
