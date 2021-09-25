package duke.task;

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
    protected TASKTYPE type;

    /**
     * Class Constructor that takes 3 parameters
     *
     * @param type Type of Task
     * @param description Task description
     * @param state whether state is Done
     */
    public Task(TASKTYPE type, String description, boolean state) {
        this(TASKTYPE.D, description);
        this.type = type;
        isDone = state;
    }

    /**
     * Class Constructor that takes 2 parameters
     *
     * @param type Type of Task
     * @param description Task description
     */
    public Task(TASKTYPE type, String description) {
        this(description);
        this.type = type;
        isDone = false;
    }

    /**
     * Class Constructor that takes 1 parameters
     * Task type is not set with this constructor
     * Completion Status is not set with this constructor
     *
     * @param description Task description
     */
    public Task(String description) {
        assert !description.isBlank() : "Task was created without a description";
        this.description = description;
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
     * Returns TASKTYPE Enum of the Task
     * If Event Task, E Enum is returned
     * If Deadline Task, D Enum is returned
     * If Todo Task, T Enum is returned
     *
     * @return Task's TASKTYPE Enum
     */
    public TASKTYPE getType() {
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
    public String getBy() {
        return null;
    }

    /**
     * Placeholder for Overriding methods
     *
     * @return Blank String
     */
    public String getDate() {
        return null;
    }

    /**
     * Placeholder for Overriding methods
     *
     * @return Blank String
     */
    public String getTime() {
        return null;
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
