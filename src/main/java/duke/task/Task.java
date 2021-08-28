package duke.task;

import java.time.format.DateTimeParseException;

/** A class that represents a task. */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with description.
     *
     * @param description The description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with description and a state whether it has been done or not.
     *
     * @param description The description.
     * @param isDone The state.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Serializes the task as a string representation.
     *
     * @return Serialized string representation.
     */
    public String serialize() {
        return this.description;
    }

    /**
     * Deserializes the string representation into a Task object.
     *
     * @param data The data to be deserialized.
     * @return The corresponding Task object.
     * @throws IllegalArgumentException If string is in invalid format.
     * @throws DateTimeParseException If date is in invalid format.
     */
    public static Task deserialize(String data) throws IllegalArgumentException, DateTimeParseException {
        try {
            // Format:
            // "taskType | isDone | description | time"
            String[] params = data.split(" \\| ");

            String taskType = params[0];
            boolean done = params[1].equals("1");
            String description = params[2];

            if (taskType.equals("T")) {
                // To-do duke.task
                return new Todo(description, done);
            } else if (taskType.equals("D")) {
                // Deadline duke.task
                String by = params[3];

                return new Deadline(description, by, done);
            } else if (taskType.equals("E")) {
                // Event duke.task
                String at = params[3];

                return new Event(description, at, done);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unable to deserialize string " + data + " -- invalid format");
        }

        throw new IllegalArgumentException("Unable to deserialize string " + data + " -- invalid format");
    }

    @Override
    public String toString() {
        String doneSymbol = this.isDone ? "[X]" : "[ ]";

        return doneSymbol + " " + this.description;
    }
}
