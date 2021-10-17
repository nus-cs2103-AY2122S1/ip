public class ToDo extends Task {
    /**
     * Constructor for todo class
     * @param description task name
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method that returns the type of task which is todo
     * @return string representing todo
     */
    @Override
    public String getTask() {
        return "T";
    }
}
