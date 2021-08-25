package duke.task;

import duke.task.Task;

public class Todo extends Task {

    /**
     * Constructs a Todo object.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Convert the task into a string that can be store in the file.
     *
     * @return the text format of the task to be store in the file.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

}
