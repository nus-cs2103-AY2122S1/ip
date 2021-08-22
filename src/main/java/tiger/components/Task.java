package tiger.components;

import tiger.exceptions.storage.TigerStorageLoadException;

/**
 * The abstract {@code Task} class encapsulates a user Task.
 */

abstract public class Task {

    /** The description of the task */
    protected String taskDescription;
    /** Whether the task is done */
    protected boolean done;

    protected Task(String taskDescription, boolean done) {
        this.taskDescription = taskDescription;
        this.done = done;
    }

    /**
     * Mark a task as done.
     */

    abstract public Task markDone();

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    abstract protected String getStorageRepresentation();

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
