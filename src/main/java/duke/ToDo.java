package duke;

/**
 * ToDo class is a task that takes in description
 */
public class ToDo extends Task {
    protected String logo = "[T]";

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns logo corresponding to type
     *
     * @return the logo [T]
     */
    public String logo() {
        return logo;
    }

    public String saveWithFormat () {
        return logo() + isDone + "_" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.description;
    }
}

