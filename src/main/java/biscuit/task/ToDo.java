package biscuit.task;

/**
 * ToDo class: For tasks without any date/time attached to it.
 * eg. visit new theme park.
 */
public class ToDo extends Task {

    /**
     * Constructs ToDo class.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructs ToDo class.
     * Used when need to set isDone.
     *
     * @param isDone      Boolean of if task is done.
     * @param description Task description.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description, TaskType.TODO);
    }

    /**
     * Returns string representation of ToDo.
     *
     * @return ToDo String.
     */
    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }
}
