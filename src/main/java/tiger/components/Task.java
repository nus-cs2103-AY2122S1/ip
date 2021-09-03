package tiger.components;

import tiger.constants.Priority;
import tiger.exceptions.storage.TigerStorageLoadException;

/**
 * The abstract {@code Task} class encapsulates a user Task.
 */

public abstract class Task {

    /** The description of the task */
    private String taskDescription;
    /** Whether the task is done */
    private boolean isDone;
    /** Priority of the task */
    private Priority priority;

    /**
     * Constructor for the {@code Task} class.
     *
     * @param taskDescription Description of the users task.
     * @param isDone Whether the task has been completed or not.
     * @param priority The priority of the task, specified by the user (if any).
     */

    protected Task(String taskDescription, boolean isDone, Priority priority) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.priority = priority;
    }

    public abstract Task markDone();

    public boolean taskIsDone() {
        return this.isDone;
    }

    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    protected abstract String getStorageRepresentation();

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
        default:
            throw new TigerStorageLoadException();
        }
    }

}
