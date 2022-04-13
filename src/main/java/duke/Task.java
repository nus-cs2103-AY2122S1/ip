package duke;

/**
 * Represents one single task.
 */
public class Task {

    /** The content of the task */
    private String description;

    /** Boolean value storing whether the task is done */
    private Boolean isDone;

    /** The category of the task in Enum */
    private Duke.Category cat;

    /** Boolean value storing whether the task already exists in the hard disk */
    private boolean isPreExisting;

    /** Parser object. */
    private Parser parser;

    /**
     * Constructor for the Task class.
     * @param description The description of the task.
     * @param cat The category of the task.
     */
    public Task(String description, Duke.Category cat) {
        this.description = description;
        this.cat = cat;
        this.isDone = false;
        this.isPreExisting = false;
        parser = new Parser(description);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param i index of task to be marked as done.
     */
    public void markAsDone(int i) {
        this.isDone = true;
    }

    /**
     * Returns the category of the task.
     *
     * @return the category of the task.
     */
    public String getCategory() {
        if (this.cat == Duke.Category.TODO) {
            return "T";
        } else if (this.cat == Duke.Category.DEADLINE) {
            return "D";
        } else {
            return "E";
        }
    }

    /**
     * Changes the status of the task icon depending on
     * whether the task is done or not.
     *
     * @return The status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : ""); // mark done task with X
    }

    /**
     * Returns whether the task already exists in the hard disk.
     *
     * @return boolean value of the isPreExisting field of the task.
     */
    public boolean getPreExisting() {
        return isPreExisting;
    }

    /**
     * Sets isPreExisting to true.
     */
    public void setPreExisting() {
        isPreExisting = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return toString of a pre-existing ie pre-loaded task.
     */
    public String getDescription() {
        return "[" + this.getCategory() + "]" + "[" + this.getStatusIcon() + "] "
                + this.description.split("\\s", 2)[1];
    }

    /**
     * Prints the task based on category.
     *
     * @return String comprising the type and content of the task.
     */
    public String toString() {
        if (this.cat == Duke.Category.TODO) {
            return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
        } else if (this.cat == Duke.Category.DEADLINE) {
            return "[D]" + "[" + this.getStatusIcon() + "] " + parser.parseTask()
                    + " (by: " + parser.parseTime() + ")";
        } else {
            return "[E]" + "[" + this.getStatusIcon() + "] " + parser.parseTask()
                    + " (at: " + parser.parseTime() + ")";
        }
    }

    /**
     * Returns the description field of the task.
     *
     * @return Description field of the task.
     */
    public String getTaskContent() {
        return this.description;
    }
}
