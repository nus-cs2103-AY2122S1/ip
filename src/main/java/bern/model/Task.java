package bern.model;

/**
 * A class to represent a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method to get whether the task is done or not and represent it with an icon.
     *
     * @return The status icon (X or empty).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * A method to return the String representation of the class.
     *
     * @return The String representation of the class.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * A method to mark this Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /***
     * A method to see whether can find the word inside the description.
     *
     * @param word The given word.
     * @return Whether can find the word or not.
     */
    public boolean canFindWord(String word) {
        String[] words = description.split("\\s+");
        for (String s : words) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
