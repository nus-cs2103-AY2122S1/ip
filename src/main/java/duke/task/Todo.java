package duke.task;

/**
 * Class that encapsulates a Todo task.
 */
public class Todo extends Task {

    /**
     * Returns a new Todo object.
     * @param todoName The user input.
     */
    public Todo(String todoName) {
        super(todoName.substring(5));
    }

    /**
     * Returns a new Todo object.
     * @param todoName The Todo as written in the save file.
     * @param isDone Whether the task is done.
     */
    public Todo(String todoName, boolean isDone) {
        super(todoName, isDone);
    }

    /**
     * Overrides the toString method in Task.
     * @return The String representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
