package duke;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the completing status of the Task object.
     *
     * @return "X" if the task is completed. " " if the task is incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns what type of task the Task object is. Should be overrided in the child class of this class.
     *
     * @return The type of task.
     */
    public abstract String typeOfTask();

    /**
     * Marks the Task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("I have marked \"" + this.description + "\" as done!");
        System.out.println(this.toString());
    }

    /**
     * Gets the description of the Task object.
     *
     * @return The description of the Task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the Task object in a string format suitable for storing in file. Should be overrided in child class.
     *
     * @return String of the Task object in the correct format for storing in file.
     */
    public abstract String saveTaskToFile();


}
