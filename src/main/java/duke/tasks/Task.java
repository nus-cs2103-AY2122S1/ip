package duke.tasks;

import duke.main.StorageElement;

/**
 * Represents an abstract class to represent Task
 */
public abstract class Task {
    protected String taskIcon;
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task object from task description
     *
     * @param description The description of task taken from user command
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates Task object from storage
     *
     * @param storageElement Storage Element object - a template for storage information
     */
    protected Task(StorageElement storageElement) {
        this.taskIcon = storageElement.getTaskIcon();
        this.description = storageElement.getDescription();
        this.isDone = storageElement.getDone();
    }

    /**
     * Returns an appropriate Task object based on storage element
     * This is like a factory methods to generate Task object
     *
     * @param storageElement Storage Element object - a template for storage information
     * @return return the Task
     */
    public static Task of(StorageElement storageElement) {
        if (storageElement.getTaskIcon().equals("T")) {
            return new Todo(storageElement);
        } else if (storageElement.getTaskIcon().equals("D")) {
            return new Deadline(storageElement);
        } else if (storageElement.getTaskIcon().equals("E")) {
            return new Event(storageElement);
        } else {
            return null;
        }
    }

    /**
     * Returns storage element to be saved to disk
     *
     * @return Storage element to be saved to disk
     */
    public abstract StorageElement getStorageElement();

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Marks this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if this task is done
     * @return True for done, False for not done
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the description of the task
     * @return description of this task
     */
    public String getDescription() {
        return this.description;
    }
}
