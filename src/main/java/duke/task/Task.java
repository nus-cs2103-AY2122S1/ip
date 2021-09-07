package duke.task;

import duke.tag.Tag;
import duke.tag.Tags;

/**
 * A Task with ability to mark as done.
 * @author KelvinSoo
 */
public abstract class Task {
    private boolean isDone;
    private final String description;
    private Tag tag = null;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public abstract String toString();

    public String getMetaData() {
        return String.format("%s|%s", isDone ? "X" : " ", description);
    }

    public void markAsDone() {
        isDone = true;
    }

    public void addTag(String tagDescription) {
        tag = Tags.tag(tagDescription);
    }

    public Tag getTag() {
        return tag;
    }

    public String showTag() {
        return tag.toString();
    }
}
