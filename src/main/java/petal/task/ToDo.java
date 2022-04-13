package petal.task;

/**
 * The To.Do class that is a subclass of Task
 * (Used a . in between to prevent IntelliJ from highlighting it)
 */
public class ToDo extends Task {

    private final String description;

    /**
     * Constructor for the To.Do class
     *
     * @param description The description of the task
     * @param isDone The boolean isDone, representing if the Task is done
     */
    public ToDo(String description, boolean isDone) {
        super(description.trim(), isDone);
        this.description = description;
    }

    @Override
    public String formatStrForSaving() {
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }

    @Override
    public boolean isTimeable() {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

