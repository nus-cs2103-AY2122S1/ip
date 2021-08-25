/**
 * A Task without any date/time attached to it.
 *
 * @author Lethicia
 */
public class ToDo extends Task{

    /**
     * Constructor for a ToDo Task.
     *
     * @param description the title or description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    //TODO
    public ToDo(String description, boolean status) {
        super(description);
        this.isDone = status;
    }

    /**
     * Returns the file's details in the format "T,<isDone>,<desc>"
     * to be stored in the hard disk
     *
     * @return formatted string containing task details
     */
    public String getText() {
        return String.format("T,%s,%s\n", this.isDone, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
