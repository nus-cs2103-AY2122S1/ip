package eightbit.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task that can be completed.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Constructs a task. The task is not done by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a task. Marks the task as done or not.
     *
     * @param description Description of the task.
     * @param isDone      Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a task. Specifies if the task is done and its tags.
     *
     * @param description Description of the task.
     * @param isDone      Whether the task is done.
     * @param tags        A list of the task's tags.
     */
    public Task(String description, boolean isDone, List<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Adds the list of tags to this task.
     *
     * @param tags List of tags to be added.
     */
    public void addTags(List<String> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Returns a string of tags separated by whitespace.
     *
     * @return Tags separated by whitespace.
     */
    public String getTagsAsString() {
        if (this.tags.size() == 0) {
            return "";
        }

        String firstTag = this.tags.get(0);
        StringBuilder sb = new StringBuilder(firstTag);
        for (int i = 1; i < tags.size(); i++) {
            String tag = " " + this.tags.get(i);
            sb.append(tag);
        }

        return sb.toString();
    }

    private String formatTagsToString() {
        if (this.tags.size() == 0) {
            return "";
        }

        String firstTag = "#" + this.tags.get(0);
        StringBuilder sb = new StringBuilder(firstTag);
        for (int i = 1; i < this.tags.size(); i++) {
            String tag = " #" + this.tags.get(i);
            sb.append(tag);
        }

        return sb.toString();
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        String result = "[" + getStatusIcon() + "] " + this.description;
        String tags = formatTagsToString();
        if (tags.equals("")) {
            return result;
        } else {
            return result + " " + tags;
        }
    }
}
