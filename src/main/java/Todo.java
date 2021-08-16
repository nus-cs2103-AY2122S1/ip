public class Todo extends Task {

    /**
     * A public constructor to initialize the task.
     *
     * @param description The given description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A public method to generate the type icon of the task.
     *
     * @return A String. The type icon of the task.
     */
    public String getTypeIcon() {
        return "T";
    }

    /**
     * A override toString() method.
     *
     * @return A String. The String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.getTypeIcon(), this.getStatusIcon(), this.description);
    }
}
