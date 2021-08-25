package duke.task;

import duke.task.Task;

/**
 * The todo task is type of Task
 */
public class Todo extends Task {

    /**
     * Basic constructor with a description
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Overridden constructor with isDOne.
     * @param description The description of the todo.
     * @param isDone Whether the task is completed or not
     */
    public Todo(String description, boolean isDone) {
        super(description,
                isDone);
    }

    /**
     * Overridden toString method to print the object
     *
     * @return String representation of the Object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Method to get the save-friendly version of the object.
     *
     * @return String of the save-friendly version of the object.
     */
    @Override
    public String saveString() {
        return String.format("T|%s|%s",
                super.description,
                super.isDone ? "1" : "0");
    }
}
