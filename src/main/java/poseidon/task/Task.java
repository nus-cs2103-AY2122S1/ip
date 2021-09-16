package poseidon.task;

import java.time.LocalDateTime;

public abstract class Task implements Comparable<Task> {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description Description of the new Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the status of this Task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Return a boolean to convey the presence of the given content in the description of the current Task object.
     *
     * @param content Content whose presence is to be checked for.
     * @return Boolean to show presence.
     */
    public boolean hasContent(String content) {
        return description.contains(content);
    }

    /**
     * Returns a human-readable string representation of the Task to be used for printing/showing to the user.
     *
     * @return String to be used for printing.
     */
    public abstract String toString();

    /**
     * Returns the string representation of the Task to be used for the purpose of storage on the hard-disk.
     *
     * @return String to be used for storage.
     */
    public abstract String toStorage();

    //@@author YeluriKetan-reused
    //Reused from https://github.com/SkyBlaise99/ip/blob/master/src/main/java/sora/task/Task.java
    // with minor modifications.
    // Original author SkyBlaise99 - https://github.com/SkyBlaise99
    protected abstract LocalDateTime getDateTime();

    @Override
    public int compareTo(Task otherTask) {
        return this.getDateTime().compareTo(otherTask.getDateTime());
    }
    //@@author
}
