package duke.task;

/**
 * The Task class encapsulates a task with a name and an indicator of its completion.
 * As every Task is one of various types of Tasks, this class is abstract.
 */
public abstract class Task {

    protected static final String DELIMITER = " | ";
    private static final String DONE_MARKER = "X";

    private String name;
    private boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    protected String generateToString(String taskInitial) {
        String doneCheckbox;
        if (isDone) {
            doneCheckbox = "[" + DONE_MARKER + "] ";
        } else {
            doneCheckbox = "[ ] ";
        }
        return "[" + taskInitial + "]" + doneCheckbox + this.name;
    }

    /**
     * Returns a String that will be stored in the taskList.txt file.
     *
     * @return a String to be stored in the taskList.txt file.
     */
    protected String generatePrintToFile(String taskInitial) {
        return taskInitial + DELIMITER + (isDone ? 1 : 0) + DELIMITER + this.name;
    };

    public abstract String printToFile();

    public String getName() {
        return this.name;
    }

    public void editName(String newName) {
        this.name = newName;
    }

}
