package poseidon.task;

import java.time.LocalDateTime;

/**
 * Represents an abstract {@code Task} class containing a {@code String} description and {@code Boolean}
 * representing the done status.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public abstract class Task implements Comparable<Task> {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} object with the given description.
     *
     * @param description Description of the new {@code Task}.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the status of this {@code Task} as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a {@code Boolean} to convey the presence of the given content in the description
     * of the current {@code Task} object.
     *
     * @param content Content whose presence is to be checked for.
     * @return {@code Boolean} to show presence.
     */
    public boolean hasContent(String content) {
        return description.contains(content);
    }

    /**
     * Returns a human-readable {@code String} representation of the {@code Task}
     * to be used for printing/showing to the user.
     *
     * @return {@code String} to be used for printing.
     */
    public abstract String toString();

    /**
     * Returns the {@code String} representation of the {@code Task}
     * to be used for the purpose of storage on the hard-disk.
     *
     * @return {@code String} to be used for storage.
     */
    public abstract String toStorage();

    //@@author YeluriKetan-reused
    //Reused from https://github.com/SkyBlaise99/ip/blob/master/src/main/java/sora/task/Task.java
    // with minor modifications. Original author SkyBlaise99 - https://github.com/SkyBlaise99
    protected abstract LocalDateTime getDateTime();

    @Override
    public int compareTo(Task otherTask) {
        return this.getDateTime().compareTo(otherTask.getDateTime());
    }
    //@@author
}
