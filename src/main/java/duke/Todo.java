package duke;

/**
 * Class to create todo tasks (tasks with no specific deadline)
 */
public class Todo extends Task {
    /**
     * To create a todo task
     * @param description the name/description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task as a string that is to be displayed to the user
     * @return the given task as a string that is to be displayed to the user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     * @return the task as a string that is to be appended to the contents of the list of
     * tasks in user's hard disk
     */
    @Override
    public String toPrintToFile() {
        return "[T]" + super.toString();
    }
}
