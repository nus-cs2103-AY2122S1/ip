package duke.task;

public class Todo extends Task {
    /**
     * A constructor for a To-do.
     * @param title a String representing the title of the to-do
     */
    public Todo(String title) {
        super(title, TypeIndicator.TODO);
    }

    /**
     * A constructor for a To-do that sets its completion status.
     * @param title a String representing the title of the to-do
     * @param isDone a boolean representing whether or not the To-do is completed
     */
    public Todo(String title, boolean isDone) {
        super(title, TypeIndicator.TODO);
        this.isDone = isDone;
    }
}