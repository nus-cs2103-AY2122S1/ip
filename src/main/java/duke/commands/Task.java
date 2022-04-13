package duke.commands;

/**
 * Encapsulates a task.
 * A task contains the description of itself,
 * a boolean of whether task is completed,
 * and also the logo tied to the task.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Task {
    /** Description of the task */
    protected String description;
    /** Status of completion of task */
    protected boolean isDone;
    /** Alphabetical logo of task */
    protected String logo;

    /**
     * Constructor for a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.logo = "N";
    }

    /**
     * Constructor for a task with a specific logo.
     *
     * @param description The description of the task.
     * @param logo The logo for the task.
     */
    public Task(String description, String logo) {
        this.description = description;
        this.isDone = false;
        this.logo = logo;
    }

    /**
     * Returns icon representation of status of task.
     * If done, return "X", otherwise a blank space.
     *
     * @return "X" if task is completed, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done, and returns string representation of task being marked done.
     * The isDone field of task is changed to true.
     *
     * @return string representation of task being marked done.
     */
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [X] %s", this.description);
    }

    /**
     * Gets logo representation of task.
     * Logo is in a single alphabet form.
     *
     * @return string representation of logo of task.
     */
    public String getLogo() {
        return this.logo;
    }

    /**
     * Checks if task is completed.
     * Boolean representation is returned.
     *
     * @return boolean representation of completion of task.
     */
    public boolean checkDone() {
        return this.isDone;
    }

    /**
     * Gets description of task.
     * The description is what user input at the start.
     *
     * @return string representation of description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns string representation of task.
     * String starts with a status icon followed by its description.
     *
     * @return string representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
