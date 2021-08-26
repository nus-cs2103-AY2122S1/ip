package task;

import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public boolean isDone() {
        return this.done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }
    
    public String serialize() {
        return this.description;
    }

    public static Task deserialize(String data) throws IllegalArgumentException, DateTimeParseException {
        try {
            // Format:
            // "taskType | isDone | description | time"
            String[] params = data.split(" \\| ");

            String taskType = params[0];
            boolean done = params[1].equals("1");
            String description = params[2];

            if (taskType.equals("T")) {
                // To-do task
                return new Todo(description, done);
            } else if (taskType.equals("D")) {
                // Deadline task
                String by = params[3];

                return new Deadline(description, by, done);
            } else if (taskType.equals("E")) {
                // Event task
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
        String doneSymbol = this.done ? "[X]" : "[ ]";

        return doneSymbol + " " + this.description;
    }
}
