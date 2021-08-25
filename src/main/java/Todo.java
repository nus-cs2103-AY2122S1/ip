/**
 * To-do class for tasks without any date/time attached to it
 *
 * @author: Chen Hsiao Ting
 */

public class Todo extends Task {

    public Todo(String description, Boolean isDone) {
        super(description, isDone, "T");
    }

    /**
     * Print the status and description of the to-do task.
     *
     * @return a string representation of the to-do task.
     */
    public String getTask() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }

}
