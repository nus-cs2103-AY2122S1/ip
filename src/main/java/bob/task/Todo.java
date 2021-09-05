package bob.task;

/**
 * Represents a special type of task with no specific deadline or time of occurrence.
 */
public class Todo extends Task {
    /**
     * Constructor for a new Todo task.
     * Calls the constructor of Task as well as the Todo task is a task.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the printTask() method in Task to print specifically the String representation of the Todo task.
     * Calls the printTask() method in Task as well to form the general part of the String representation.
     *
     * @return String representation of the Todo task.
     */
    @Override
    public String printTask() {
        String todoHeader = "[T] ";
        return todoHeader + super.printTask();
    }
}
