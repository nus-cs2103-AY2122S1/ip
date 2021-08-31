package duke.tasks;

import java.util.HashSet;

/** Abstract class representing a task */
public abstract class Task {

    private boolean done = false;
    private String taskDetails;

    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Marks the task as done and checks if it was previously completed.
     *
     * @return boolean indicating whether the task status has been changed from not done to done.
     */
    public boolean markDone() {
        if (!done) {
            done = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of data stored in this task.
     *
     * @return String containing the task's data, which will be used for saving to disk.
     */
    public boolean containsKeywords(HashSet<String> keywords) {
        String[] words = this.taskDetails.split(" ");
        for (String word : words) {
            if (keywords.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public String toDataString() {
        return String.format("%d | %s", done ? 1 : 0, taskDetails);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", taskDetails);
    }
}
