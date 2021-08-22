/**
 * ToDo class.
 * Used to represent a todo task.
 *
 * @author KelvinSoo
 * @version Level-4
 *
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String descripton, Boolean isDone) {
        super(descripton);
        if (isDone) {
            super.markAsDone();
        }
    }
    @Override
    public String getMetaData() {
        return String.format("T|%s", super.getMetaData());
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }
}
