package duke.task;

import java.time.LocalDate;

/**
 * Class for Todo type Task objects.
 */

public class Todo extends Task {
    /**
     * Basic constructor for Todo Task objects.
     *
     * @param label description of the Task.
     */
    public Todo(String label) {
        super(label);
    }

    /**
     * Getter for type.
     *
     * @return "T" as an identifier for other parts of the application.
     */
    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Not to be called from Todo tasks.
     */
    @Override
    public String getDate() {
        assert false;
        return "";
    }

    /**
     * Not to be called from Todo tasks.
     */
    @Override
    public void setDate(LocalDate date) {
        assert false;
    }
}
