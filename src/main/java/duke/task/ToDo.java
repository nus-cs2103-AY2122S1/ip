package duke.task;

public class ToDo extends Task {
    /**
     * Constructs a "to do" instance.
     *
     * @param description Description of the "to do" instance.
     */
    protected ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a "to do" instance.
     *
     * @param description Description of the "to do" instance.
     * @param isDone Has the task been done?
     */
    protected ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String populateSaveData() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + this.description + " | "
                + "todo"; // The third part should be ignored.
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            return ((ToDo) o).isDone == this.isDone
                    && ((ToDo) o).description.equals(this.description);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
