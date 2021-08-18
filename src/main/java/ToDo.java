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

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }
}
