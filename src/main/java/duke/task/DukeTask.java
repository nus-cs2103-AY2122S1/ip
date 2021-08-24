package duke.task;

import duke.exception.TaskParseException;

public abstract class DukeTask {
    public final String name;
    public boolean done;

    public DukeTask(String name) {
        this(name, false);
    }

    DukeTask(String name, boolean isDone) {
        this.name = name;
        this.done = isDone;
    }

    public boolean isDone() {
        return done;
    }

    public void markAsDone() {
        done = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", name);
    }

    /**
     * Returns the task as a serialized string. Used by {@link duke.Storage} to store tasks.
     * @return a serialized string representing the task
     */
    public abstract String toSerializedString();

    /**
     * Creates a task from the given string. Used by {@link duke.Storage} to read stored tasks.
     * @param str the serialized task
     * @return the task
     * @throws TaskParseException if the given string is in the wrong format
     */
    public static DukeTask fromSerializedString(String str) throws TaskParseException {
        // Either
        // name/done/simple
        // name/done/by/deadline
        // name/done/at/date
        String[] tokens = str.split("/", 4);

        String name = tokens[0];
        boolean isDone = Integer.parseInt(tokens[1]) == 1;
        String type = tokens[2];

        switch (type) {
        case "simple":
            return new DukeSimpleTask(name, isDone);
        case "by":
            String deadline = tokens[3];
            return new DukeDeadlineTask(name, isDone, deadline);
        case "at":
            String date = tokens[3];
            return new DukeEvent(name, isDone, date);
        default:
            throw new TaskParseException();
        }
    }
}
