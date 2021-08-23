package petal.task;

/**
 * The To.Do class that is a subclass of Task
 * (Used a . in between to prevent IntelliJ from highlighting it)
 */
public class ToDo extends Task {

    /**
     * Constructor for the To.Do class
     *
     * @param description The description of the task
     * @param isDone The boolean isDone, representing if the Task is done
     */
    public ToDo(String description, boolean isDone) {
        super(description.trim(), isDone);
    }

    /**
     * Method that formats the string representation of the current To.Do to allow
     * it to be sorted in a readable format in the Tasks.txt and retrieved again
     *
     * @return Formatted string representation of the object
     */
    @Override
    public String strForSaving() {
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }

    /**
     * Overriden method isTimeable
     *
     * @return False since not Timeable
     */
    @Override
    public boolean isTimeable() {
        return false;
    }

    /**
     * Overridden toString method for the To.Do class
     *
     * @return String representation of the To.Do object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

