package duke.tasks;

import duke.exceptions.TodoException;

/**
 * Represent the task of type Todo.
 */
public class ToDo extends Task {
    /**
     * Creates an Todo task object.
     *
     * @param description Description of what the Todo task is about.
     * @throws TodoException If the Todo object cannot be created successfully due to empty description.
     */
    public ToDo(String description) throws TodoException {
        super(description);
        if(description.equals("todo")) {
            throw new TodoException();
        }
    }

    /**
     * Gets the information of the task such as its description and deadline to store in the hard disk.
     *
     * @return String that contains the information.
     */
    public String getInfo() {
        return getDescription();
    }

    /**
     * Gets the type of the task and denotes it with a letter to store in the hard disk.
     *
     * @return The letter that indicates the type of the task.
     */
    public String getType() {
        return "T";
    };

    /**
     * Stores the task in the hard disk.
     *
     * @return String denoting the Todo task which will be stored in the hard disk.
     */
    @Override
    public String storeItem() {
        return "T/" + getDone() + "/" + getDescription();
    };

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the todo task created.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
