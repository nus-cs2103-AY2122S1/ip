package duke.tasks;

import java.util.Arrays;
import java.util.HashSet;

/** Abstract class representing a task */
public abstract class Task {

    private boolean isDone = false;
    private String taskDetails;

    /**
     * Task constructor.
     *
     * @param taskDetails String describing the task.
     */
    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
        assert (!taskDetails.trim().equals("")) : "taskDetails should not be empty String";
    }

    /**
     * Marks the task as done and checks if it was previously completed.
     *
     * @return boolean indicating whether the task status has been changed from not done to done.
     */
    public boolean markDone() {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the task matches all keywords, and false otherwise.
     *
     * @return boolean indicating whether the task contains all the keywords.
     */
    public boolean containsKeywords(HashSet<String> keywords) {
        HashSet<String> words = new HashSet<>(Arrays.asList(this.taskDetails.split(" ")));
        words.retainAll(keywords);
        return words.equals(keywords);
    }

    /**
     * Returns a String which is used to save data to disk.
     *
     * @return String representation of how data will be saved to disk.
     */
    public String toDataString() {
        return String.format("%d | %s", isDone ? 1 : 0, taskDetails);
    }

    /**
     * Returns a String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", taskDetails);
    }

    /**
     * Indicates whether some other object is "equal to" this one. Done status
     * does not affect equality of objects since no two tasks are supposed to
     * have the same description.
     *
     * @param obj Object to compare to
     * @return boolean indicating whehter the other object is "equal" to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Task) {
            Task tmp = (Task) obj;
            return taskDetails.equals(tmp.taskDetails);
        } else {
            return false;
        }
    }
}
