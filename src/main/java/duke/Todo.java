package duke;

/**
 * Class for Todo, a child class of Task.
 * @author Liew Jian Hong
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo task.
     *
     * @param desc String array consisting of parsed description.
     */
    public Todo(String[] desc) {
        super(desc[1], Boolean.valueOf(desc[3]));
    }

    /**
     * Converts the task into a form that writes into storage.
     *
     * @return String representation of task to write into storage.
     */
    public String toWrite() {
        return "todo--" + Boolean.toString(this.isDone) + "--" + this.desc + "--0\n";
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return Return the type, completion status and description of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
