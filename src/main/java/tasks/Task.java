package tasks;

import data.TaskStorage;
import exceptions.InvalidTaskNumberException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a user-added task
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
     * @param taskRepresentation comma separated String representation of a task
     * @return Task with data extracted from the given String representation of the Task
     */
    public static Task getTaskFromRepresentation(String taskRepresentation) {
        String[] taskData = taskRepresentation.split(",");
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
    }

    /**
     * Set description of task.
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

    /**
     * Convert task data to representation to be saved in file.
     *
     * @return representation of task data
     */
    public String getTaskRepresentation() {
        return (isDone ? "X," : ",") + description + ",";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
