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
        super(desc[1], Boolean.parseBoolean(desc[3]), new Place(desc[4]));
    }

    /**
     * Converts the task into a form that writes into storage.
     *
     * @return String representation of task to write into storage.
     */
    public String toWrite() {
        return "todo--" + this.isDone + "--" + this.desc + "--0--" + this.location.toWrite();
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return Return the type, completion status and description of the todo.
     */
    @Override
    public String toString() {
        String location = this.location.toString() == "" ? "" : " at " + this.location.toString();
        return "[T]" + super.toString() + location + "\n";
    }
}
