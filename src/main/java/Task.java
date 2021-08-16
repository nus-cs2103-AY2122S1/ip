/**
 *  This class represents the tasks in a list
 * @author Ryan Tian Jun
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private TYPE type;

    protected enum TYPE {
        T, D, E, O
    }


    public Task(String description, TYPE type) {
        this.description = description;
        this.isDone = false;
        if (type == TYPE.T) {
            this.type = TYPE.T;
        } else if (type == TYPE.D) {
            if (description.contains("/by")) {
                this.description = description.substring(0, description.indexOf("/by"));
            }
            this.type = TYPE.D;
        } else if (type == TYPE.E) {
            if (description.contains("/at")) {
                this.description = description.substring(0, description.indexOf("/at"));
            }
            this.type = TYPE.E;
        } else {
            this.type = TYPE.O;
        }
    }


    /**
     * Marks task as done
     *
     * @return returns true if it was successful
     */
    public boolean markAsDone() {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
            return false;
        }
    }


    // return task description
    private String getDescription() {
        return description;
    }

    // return task type
    private String getType() {
        return "[" + type.toString() + "]";
    }

    // return task status
    private String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}
