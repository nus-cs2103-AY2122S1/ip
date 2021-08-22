package tiger.components;

import tiger.exceptions.storage.TigerStorageLoadException;

public class ToDo extends Task{

    /**
     * Private constructor for {@code ToDo} class.
     *
     * @param taskDescription description of Task.
     * @param done whether the Task is done.
     */

    private ToDo(String taskDescription, boolean done) {
        super(taskDescription, done);
    }

    /**
     * Public constructor for {@code ToDo} class.
     *
     * @param taskDescription description of Task.
     * @param done whether the Task is done.
     */

    public static ToDo of(String taskDescription, boolean done) {
        return new ToDo(taskDescription, done);
    }

    /**
     * Mark a {@code ToDo} as done.
     */

    @Override
    public ToDo markDone() {
        return new ToDo(taskDescription, true);
    }

    /**
     * Gets the String representation of {@code ToDo}.
     *
     * @return the String representation of {@code ToDo}.
     */

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[T] [X] %s", this.taskDescription);
        } else {
            return String.format("[T] [ ] %s", this.taskDescription);
        }
    }

    /**
     * Returns a String to be written to storage. The {@code Storage} class knows how to parse
     * this string when it is loaded later.
     *
     * @return the String to be written to storage.
     */

    protected String getStorageRepresentation() {
        return String.format("T;%s;%s", this.done, this.taskDescription);
    }

    /**
     * Given a String loaded from storage, interpret it and return its corresponding {@code Event}
     * @param s String loaded from storage.
     * @return the corresponding {@code Event} object.
     * @throws TigerStorageLoadException if the loaded string is corrupted, or if there is so {@code IOException}.
     */

    protected static ToDo getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        /* s should be of the form T|true/false|taskDescription| */
        String[] stringArray = s.split(";");
        int length = stringArray.length;
        try {
            assert (length == 3);
            // check if task is indeed a ToDo task
            assert (stringArray[0].equals("T"));
            // check task done value is either true or false
            assert (stringArray[1].equals("true") || stringArray[1].equals("false"));
            // check that task description is non-empty
            assert (!stringArray[2].equals(""));
            if (stringArray[1].equals("true")) {
                return new ToDo(stringArray[2], true); // task description, done
            } else {
                return new ToDo(stringArray[2], false); // task description, done
            }
        } catch (AssertionError e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }
}
