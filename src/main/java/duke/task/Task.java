package duke.task;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/** A class that represents a task. */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructs a task with description.
     *
     * @param description The description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
    }

    /**
     * Constructs a task with description and done state.
     *
     * @param description The description.
     * @param isDone The state whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.priority = Priority.MEDIUM;
    }

    /**
     * Constructs a task with description, priority and a state whether it has been done or not.
     *
     * @param description The description.
     * @param priority The priority.
     * @param isDone The state.
     */
    public Task(String description, Priority priority, boolean isDone) throws DukeException {
        if (priority == null) {
            throw new DukeException("Invalid priority value.");
        }

        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets priority of current task.
     *
     * @param priority The priority.
     * @throws DukeException If priority is null.
     */
    public void setPriority(Priority priority) throws DukeException {
        if (priority == null) {
            throw new DukeException("Invalid priority value");
        }

        this.priority = priority;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Serializes the task as a string representation.
     *
     * @return Serialized string representation.
     */
    public String serialize() {
        return this.priority + " | " + this.description;
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
            // "taskType | priority | isDone | description [| time]"
            String[] params = data.split(" \\| ");

            String taskType = params[0];
            Priority priority = Priority.of(params[1]);
            boolean done = params[2].equals("1");
            String description = params[3];

            if (taskType.equals("T")) {
                // To-do task
                return new Todo(description, priority, done);
            } else if (taskType.equals("D")) {
                // Deadline task
                String by = params[4];

                return new Deadline(description, priority, by, done);
            } else if (taskType.equals("E")) {
                // Event task
                String at = params[4];

                return new Event(description, priority, at, done);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unable to deserialize string " + data + " -- invalid format");
        }

        throw new IllegalArgumentException("Unable to deserialize string " + data + " -- invalid format");
    }

    @Override
    public String toString() {
        String doneSymbol = this.isDone ? "[X]" : "[ ]";

        return doneSymbol + " [" + this.priority + "] " + this.description;
    }
}
