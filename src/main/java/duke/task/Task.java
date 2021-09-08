package duke.task;

import java.time.LocalDate;

/**
 * @author Dr-Octavius
 *
 * Class that represents a Task.
 *
 * @version 1.0
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected TASK_TYPE type;

    /**
     * Class Constructor that takes 2 parameters
     *
     * @param description Task description
     * @param type Type of Task
     */
    public Task(String description, TASK_TYPE type) {
        this(description);
        this.type = type;
        isDone = false;
    }

    /**
     * Class Constructor that takes 1 parameters
     * Task type is not set with this constructor
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    private String getTypeIcon() {
        String out;
        switch (type) {
        case T:
            out = "T";
            break;
        case D:
            out = "D";
            break;
        case E:
            out = "E";
            break;
        default:
            out = " ";
            break;
        }
        return out;
    }

    /**
     * Returns description of the Task
     *
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns TASK_TYPE Enum of the Task
     * If Event Task, E Enum is returned
     * If Deadline Task, D Enum is returned
     * If Todo Task, T Enum is returned
     *
     * @return Task's TASK_TYPE Enum
     */
    public TASK_TYPE getType() {
        return type;
    }

    /**
     * Returns done state of task
     *
     * If Task is done, true is returned
     * If Taks is not done, false is returned
     *
     * @return Task done State
     */
    public boolean getState() {
        return isDone;
    }

    /**
     * Placeholder for Overriding methods
     *
     * @return current date
     */
    public LocalDate getBy() {
        return LocalDate.now();
    }

    /**
     * Placeholder for Overriding methods
     *
     * @return Blank String
     */
    public String getAt() {
        return " ";
    }

    /**
     * Sets the Task to done
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * String representation of a Task
     *
     * @return Summary of Task information
     */
    @Override
    public String toString() {
        return "["
                .concat(getTypeIcon())
                .concat("][")
                .concat(getStatusIcon())
                .concat("] ")
                .concat(getDescription());
    }
}
