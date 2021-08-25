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
     * @param number takes in a integer representing the task number
     */
    public Deadline(String description,String by,int number) {
        super(description,number);
        this.by = by;
    }

    /**
     * getter method to get task deadline
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
