package duke;

/**
 * Creates Todo tasks with descriptions.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task from user command with the correct description, done status is false by default.
     *
     * @param description describes the nature of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a Todo task from a disk task string with the correct description and done status.
     *
     * @param num number retrieved from the disk that determines if the task is done or not.
     * @param description describes the nature of the task.
     */
    public Todo(String num, String description) {
        super(description);
        this.isDone = !num.equals(NOT_DONE_STRING);
    }

    /**
     * Returns the string format in which to store the task to the disk, which is T | 1 or 0 | description.
     *
     * @return new string format in which to store the task to the disk which is different from toString.
     */
    @Override
    public String getFileString() {
        return String.format("T | %d | %s", this.isDone ? DONE : NOT_DONE, this.description);
    }

    /**
     * Returns a string in the form of "[T] (done status) (description)" when task is printed.
     *
     * @return string that is displayed when task is printed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
