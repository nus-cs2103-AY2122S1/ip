package duke;

public class Task {
    /** The content of the task */
    private String description;

    /** Boolean value storing whether the task is done */
    private Boolean isDone;

    /** The category of the task in Enum */
    private Duke.Category cat;

    /** Boolean value storing whether the task already exists in the hard disk */
    private boolean isPreExisting;

    private Parser parser;

    public Task(String description, Duke.Category cat) {
        this.description = description;
        this.cat = cat;
        this.isDone = false;
        this.isPreExisting = false;
        parser = new Parser(description);
    }
    /**
     * Marks a task in the list as done.
     */
    public void markAsDone(int i) {
        this.isDone = true;
    }


    /**
     * Changes the status of the task icon depending on
     * whether the task is done or not.
     * @return The status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");// mark done task with X
    }

    /**
     * Returns whether the task already exists in the hard disk.
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
     * @return
     */
    public String getDescription() {

        return "[" + this.description.charAt(1) + "]" + "[" + this.getStatusIcon() + "] " + this.description.split("\\s", 3)[2];

    }


    /**
     * Custom prints the task based on category.
     * @return String comprising the type and content of the task.
     */
    public String toString() {
        if (this.cat == Duke.Category.TODO) {
            return "[T]" + "[" + this.getStatusIcon() + "] " + parser.parseTask();
        } else if (this.cat == Duke.Category.DEADLINE) {
            return "[D]" + "[" + this.getStatusIcon() + "] " + parser.parseTask() + " (by: " + parser.parseTime() + ")";
        } else {
            return "[E]" + "[" + this.getStatusIcon() + "] " + parser.parseTask() + " (at: " + parser.parseTime() + ")";
        }
    }




}
