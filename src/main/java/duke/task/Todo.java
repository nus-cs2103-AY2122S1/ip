package duke.task;

class Todo extends Task {
    /**
     * Constructor for a `Todo` task.
     *
     * @param isDone      Indicates whether the task has been completed.
     * @param description Task description.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveAsString() {
        return 0 + "\n" + super.saveAsString() + "\n";
    }
}
