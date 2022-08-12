package duke.task;

/**
 * Class to store task. Subclass of duke.task.Task.
 *
 * @author marcuspeh
 * @version A-MoreOOP
 * @since 21 Aug 2021
 */
public class ToDos extends Task {
    /**
     * Constructor for Todo.
     *
     * @param task task to be stored.
     */
    public ToDos(String task) {
        super(task);
    }

    /**
     * Constructor for Todo.
     *
     * @param task duke.task.Task to be stored.
     * @param done Whether the task is done.
     */
    public ToDos(String task, boolean done) {
        super(task, done);
    }

    /**
     * Saves the task to the txt file.
     * Format is as follow: {@literal <}Type(T){@literal >} | {@literal <}Description{@literal >} |
     * {@literal <}Done{@literal >}.
     *
     * @return string to save the txt file.
     */
    public String saveOutput() {
        return String.format("T | %s | %s", super.getTask(), super.getIsDone() ? 1 : 0);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
