package tiger.components;

import tiger.constants.Priority;
import tiger.exceptions.storage.TigerStorageLoadException;

/**
 * The abstract {@code Task} class encapsulates a user Task.
 */

abstract public class Task {

    /** The description of the task */
    protected String taskDescription;
    /** Whether the task is done */
    protected boolean done;
    /** Priority of the task */
    protected Priority priority;

    protected Task(String taskDescription, boolean done) {
        this.taskDescription = taskDescription;
        this.done = done;
        this.priority = Priority.MEDIUM;
    }

    protected Task(String taskDescription, boolean done, Priority priority) {
        this.taskDescription = taskDescription;
        this.done = done;
        this.priority = priority;
    }

    /**
     * Mark a task as done.
     */

    abstract public Task markDone();

    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    abstract protected String getStorageRepresentation();

    /**
     * Get the task description.
     *
     * @return the task description.
     */

    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Given a String loaded from storage, interpret it and return its corresponding {@code Task}
     * @param s String loaded from storage.
     * @return the corresponding {@code Task} object.
     * @throws TigerStorageLoadException if the loaded string is corrupted, or if there is so {@code IOException}.
     */

    protected static Task getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        String[] stringArray = s.split(";");
        switch (stringArray[0]) {
        case "T":
            return ToDo.getTaskFromStringRepresentation(s);
        case "E":
            return Event.getTaskFromStringRepresentation(s);
        case "D":
            return DeadLine.getTaskFromStringRepresentation(s);
        }
        throw new TigerStorageLoadException("");
    }

}
