package duke.task;

import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
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
