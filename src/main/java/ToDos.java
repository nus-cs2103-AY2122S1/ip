/**
 * Class to store task. Subclass of Task.
 *
 * @author marcuspeh
 * @version Level-7
 * @since 19 Aug 2021
 */
public class ToDos extends Task {
    /**
     * Constructor for Todo.
     *
     * @param task task to be stored
     */
    ToDos(String task) {
        super(task);
    }

    /**
     * Constructor for Todo.
     *
     * @param task Task to be stored
     * @param done Whether the task is done
     */
    ToDos(String task, boolean done) {
        super(task, done);
    }

    /**
     * To save the task to the txt file.
     * Format is as follow: <Type(T)> | <Description> | <Done>
     *
     * @return string to save the txt file
     */
    public String saveOutput() {
        return String.format("T | %s | %s", super.getTask(), super.getIsDone() ? 1 : 0);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
