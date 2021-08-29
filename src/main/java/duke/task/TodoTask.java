package duke.task;

public class TodoTask extends Task {

    /**
     * Constructor for a TodoTask object.
     * @param name name of task.
     * @param isDone whether or not task is done.
     */
    public TodoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String formatForFile() {
        return "T" + super.formatForFile() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
