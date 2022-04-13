package tiger.components;

import tiger.constants.Priority;
import tiger.exceptions.storage.TigerStorageLoadException;

public class ToDo extends Task {

    /**
     * Constructor for the {@code ToDo} class.
     *
     * @param taskDescription Description of the users task.
     * @param isDone          Whether the task has been completed or not.
     * @param priority        The priority of the task, specified by the user (if any).
     */

    public ToDo(String taskDescription, boolean isDone, Priority priority) {
        super(taskDescription, isDone, priority);
    }

    /**
     * Given a String loaded from storage, interpret it and return its corresponding {@code Event}
     *
     * @param s String loaded from storage.
     * @return the corresponding {@code Event} object.
     * @throws TigerStorageLoadException if the loaded string is corrupted, or if there is so {@code IOException}.
     */

    protected static ToDo getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        String[] stringArray = s.split(";");
        int length = stringArray.length;
        try {
            assert (length == 4);
            // check if task is indeed a ToDo task
            assert (stringArray[0].equals("T"));
            // check task done value is either true or false
            assert (stringArray[1].equals("true") || stringArray[1].equals("false"));
            // check that task description is non-empty
            assert (!stringArray[2].equals(""));
            assert (stringArray[3].equals("L") || stringArray[3].equals("M") || stringArray[3].equals("H"));
            Priority p = Priority.getPriorityFromLetter(stringArray[3]);
            if (p.equals(Priority.INVALID)) {
                // this is not needed if we compile with assertions
                throw new TigerStorageLoadException();
            }
            if (stringArray[1].equals("true")) {
                return new ToDo(stringArray[2], true, p); // task description, done, priority
            } else {
                return new ToDo(stringArray[2], false, p); // task description, done, priority
            }
        } catch (AssertionError e) {
            throw new TigerStorageLoadException();
        }
    }

    /**
     * Mark a {@code ToDo} as done.
     */

    @Override
    public ToDo markDone() {
        return new ToDo(this.getTaskDescription(), true, this.getPriority());
    }

    /**
     * Gets the String representation of {@code ToDo}.
     *
     * @return the String representation of {@code ToDo}.
     */

    @Override
    public String toString() {
        if (this.taskIsDone()) {
            return String.format("[T] [X] %s", this.getTaskDescription());
        } else {
            return String.format("[T] [%s] %s", this.getPriority().getLetter(), this.getTaskDescription());
        }
    }

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    protected String getStorageRepresentation() {
        return String.format("T;%s;%s;%s", this.taskIsDone(), this.getTaskDescription(),
                this.getPriority().getLetter());
    }
}
