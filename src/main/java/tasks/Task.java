package tasks;

import java.util.ArrayList;
import exceptions.EmptyDescException;
import duke.Tag;

/**
 * A Task object represent a task in Duke
 * A Task contains a description and a check for whether it has been done
 */

public class Task {

    // The string that describes the task
    String desc;
    boolean isDone;
    ArrayList<Tag> tags;

    public Task(String desc, boolean isDone) throws EmptyDescException {
        if (desc.isBlank()) {
            throw new EmptyDescException();
        }
        this.desc = desc;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    public Task(String desc, boolean isDone, String[] tags) throws EmptyDescException {
        if (desc.isBlank()) {
            throw new EmptyDescException();
        }
        this.desc = desc;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
        for (String tag : tags) {
            this.tags.add(new Tag(tag));
        }
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the task description
     * @return The task description
     */
    public String getDescription() {
        return this.desc;
    }

    /**
     * Adds a tag to the task
     * @param tagNames The name of tags to be added
     */
    public void addTags(String[] tagNames) {
        for (String tagName : tagNames) {
            Tag tag = new Tag(tagName);
            this.tags.add(tag);
        }
    }

    /**
     * Writes all tags attached to this task in a readable form.
     * @return A string consisting of all tags attached to the task.
     */
    protected String getTagString() {
        StringBuilder sb = new StringBuilder();
        int numberOfTags = tags.size();
        if (numberOfTags > 0) {
            for (int i = 0; i < numberOfTags - 1; i++) {
                sb.append(tags.get(i) + ", ");
            }
            sb.append(tags.get(numberOfTags - 1));
        } else {
            return "-";
        }
        return sb.toString();
    }

    /**
     * Provides the String representation of the task in the format
     * meant for writing to the file.
     *
     * @return The string representation of this deadline for the file to be saved to.
     */
    public String getSaveText() {
        int isDone = this.isDone ? 1 : 0;
        return "T | " + isDone + " | " + desc + " | " + this.getTagString() + "\n";
    }

    String completionStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String toString() {
        return this.completionStatus() + desc;
    }
}
