package duke;

/**
 * A type of <code>Task</code> that has a description attached to it.
 */
public class ToDo extends Task {

    /**
     * Returns a ToDo object.
     *
     * @param description description of ToDo
     * @param isDone indicates if ToDo has been completed
     * @param hasNotif indicates if a notif needs to be sent to user for the creation of this ToDo.
     */
    public ToDo(String description, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.category = Category.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

