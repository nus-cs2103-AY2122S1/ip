/**
 * This class encapsulates the Todo Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * This function returns the string of the task to be represented in the list.
     *
     * @returns the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + description;
    }

    /**
     * This function returns the string of the task to be represented in the text file.
     *
     * @returns the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() { return "T@" + (isDone ? 1 : 0) + "@" + this.description; }
}
