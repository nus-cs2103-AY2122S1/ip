package angrybot.task;

/**
 * Encapsulate the representation of a task.
 * The task has a description of what the task is about,
 * and a boolean to store if the task is done.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    /**
     * A one letter character to represent the type of task.
     */
    protected final char representation;

    /**
     * Constructor for a task.
     *
     * @param description    The description of the task.
     * @param isDone         Boolean representing if task is done.
     * @param representation The type of task.
     */
    public Task(String description, boolean isDone, char representation) {
        this.description = description;
        this.isDone = isDone;
        this.representation = representation;
    }

    /**
     * Returns a string containing the type of task,
     * whether the task is done, and its description.
     *
     * @return The string containing the information of the task.
     */
    public String checkStatus() {
        return String.format("[%s][%s] %s", representation, (isDone ? 'X' : ' '), description);
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task.
     * Used in storing of data to local directory.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s|%c|%s", representation, (isDone ? '1' : '0'), description);
    }

    /**
     * Returns true if the task description contains the keyword provided by the user.
     *
     * @param s The keyword to search for in the description.
     * @return True if the description contains the keyword, else false.
     */
    public boolean findKeyword(String s) {
        return description.contains(s);
    }

    /**
     * Compares the task description with another task.
     *
     * @param t The task to be compared with.
     * @return -1 if the description for this task is lexicographically smaller, 0 if same, else 1.
     */
    public int compareTo(Task t) {
        return description.compareTo(t.description);
    }
}
