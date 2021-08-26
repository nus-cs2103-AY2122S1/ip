/**
 * a class to encapsulate tasks without any date/time attached.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */

public class Todo extends Task {
    
    private final String TASK_MARKER = "T";

    /**
     * Class constructor for Todo class.
     * 
     * @param description the task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * prints out the Todo task.
     * 
     * @return string format of the todo task, 
     * consisting of the task marker "T" and task description.
     */
    @Override
    public String toString() {
        return String.format("%s | %s", TASK_MARKER, super.toString());
    }

    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }
}
