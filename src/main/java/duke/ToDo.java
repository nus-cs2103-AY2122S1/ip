package duke;

/**
 * Class includes methods required for creating a todoTask
 * and obtaining information relating to it.
 */
public class ToDo extends Task {
    private String type;

    /**
     * Creates a todoTask.
     *
     * @param information refers to details of task
     * @param type refers to type of task
     */
    public ToDo(String information, String type) {
        super(information);
        this.type = type;
    }

    /**
     * Returns todoTask in a string format.
     *
     * @return todoTask formatted as a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns type of task in a string format.
     *
     * @return task type
     */
    public String getType() {
        return type;
    }

}