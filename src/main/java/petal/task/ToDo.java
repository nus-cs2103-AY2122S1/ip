package petal.task;

/**
 * The To.Do class that is a subclass of Task
 * (Used a . in between to prevent IntelliJ from highlighting it)
 */
public class ToDo extends Task {


    /**
     * Constructor for the To.Do class
     * @param description The description of the task
     */
    public ToDo(String description, boolean isDone) {
        super(description.trim(), isDone);
    }

    @Override
    public String strForSaving() {
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }

    /**
     * Overridden toString method for the To.Do class
     * @return String representation of To.Do object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

