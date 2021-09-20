package uhtredragnarson.task;

/**
 * The ToDo class extends the Task class and represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructor for this class.
     *
     * @param title Description of the to-do.
     */
    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.title;
    }
}
