package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a todo task in the task list.
 */
public class Todo extends Task {
    /**
     * Represents a constructor for the Todo class where the description of task is initialized.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task that is stored on duke.txt.
     *
     * @return String representation of the todo task stored on duke.txt.
     */
    public String getTaskListOnDisk() {
        return "duke.task.Todo |" + super.getStatusIcon() + "| " + description;
    }

    /**
     * Returns the string representation of the todo task that is stored in the task list.
     *
     * @return String description of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public LocalDateTime getDate() {
        return null;
    }
    
    public String getDescription() {
        return this.description;
    }
}
