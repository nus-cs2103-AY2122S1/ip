package duke.task;

public class ToDo extends Task{

    /**
     * Constructor for ToDo Task.
     *
     * @param description String description of ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
