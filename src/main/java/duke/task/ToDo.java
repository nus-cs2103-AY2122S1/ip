package duke.task;

/**
 * Basic Task to be Done.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param str Description of Task
     */
    public ToDo(String str) {
        super(str);
    }

    /**
     * Constructor for ToDo.
     *
     * @param isDone String representation of task being done
     * @param desc   Description of Task
     */
    public ToDo(String isDone, String desc) {
        super(isDone, desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }
}
