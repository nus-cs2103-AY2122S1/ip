package duke.task;

/**
 * Todo is a Task with no due date.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
//Solution below adapted from https://github.com/jovyntls/ip
public class ToDo extends Task {
    /**
     * A constructor for a To-do.
     * @param title a String representing the title of the to-do
     */
    public ToDo(String title) {
        super(title, Type.TODO);
    }

    /**
     * A constructor for a To-do that sets its completion status.
     * @param title a String representing the title of the to-do
     * @param isDone a boolean representing whether or not the To-do is completed
     */
    public ToDo(String title, boolean isDone) {
        super(title, Type.TODO);
        this.isDone = isDone;
    }
}
