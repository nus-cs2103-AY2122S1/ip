package agent.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import agent.exceptions.InvalidTaskDataException;

/**
 * This class encapsulates a user-added task.
 *
 * @author kevin9foong
 */
public abstract class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Extracts data from given taskRepresentation and returns the specific subclass of
     * Task associated with the given String task representation.
     *
     * @param taskRepresentation comma separated String representation of a task.
     * @return Task with data extracted from the given String representation of the Task.
     * @throws InvalidTaskDataException thrown when the String representation of Task is invalid.
     */
    public static Task getTaskFromRepresentation(String taskRepresentation) throws InvalidTaskDataException {
        String[] taskData = taskRepresentation.split(" \\|;; ");
        try {
            boolean isDone = taskData[1].equals("X");

            switch (TaskType.valueOf(taskData[0])) {
            case DEADLINE:
                return new Deadline(taskData[2], isDone, LocalDate.parse(taskData[3]));
            case EVENT:
                return new Event(taskData[2], isDone, taskData[3]);
            case TODO:
                return new ToDo(taskData[2], isDone);
            default:
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new InvalidTaskDataException();
        }
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description of this task.
     *
     * @param description description to be set as.
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Converts this task's data to representation to be saved in file.
     *
     * @return representation of this task's data
     */
    public String getTaskRepresentation() {
        return (isDone ? "X |;; " : " |;; ") + description + " |;; ";
    }

    /**
     * Returns textual representation of this <code>Task</code> and its data.
     *
     * @return String representing this <code>Task</code> and its data.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
