package duke.task;

/**
 * ToDo is a Task that encapsulates the attributes and behaviour of a Task to be done.
 *
 * @author leezhixuan
 */
public class ToDo extends Task {

    /**
     * Creates an instance of ToDo
     *
     * @param name Name of ToDo
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.getName();
    }

    @Override
    public String logo() {
        return "[T]";
    }
}
