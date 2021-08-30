/**
 * A To-do kind of Task.
 * @author Thomas Hogben
 */
public class ToDo extends Task {

    /**
     * @param description The description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }


    @Override
    public String getSave() {
        return "T" +
                (this.isDone() ? "1" : "0") + "|" +
                this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
