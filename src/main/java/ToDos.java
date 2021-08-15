/**
 * Class to store task. Subclass of Task.
 *
 * @author marcuspeh
 * @version Level-4
 * @since 15 Aug 2021
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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
