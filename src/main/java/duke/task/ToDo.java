package duke.task;

/**
 * Represents a ToDo task.
 * A ToDo task contains a String describing the task.
 */

public class ToDo extends Task {

    /**
     * Creates a ToDo Object containing the task to be done.
     * @param toDo task to be done.
     */
    public ToDo(String toDo) {
        super(toDo);
    }

    String getType() {
        return "T";
    }

    /**
     * returns a String in the format "[T] Completion Status | Task"
     * @return a String in the format "[T] Completion Status | Task"
     */

    public String getToWrite() {
        return this.getType() + " | " + super.getToWrite();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}