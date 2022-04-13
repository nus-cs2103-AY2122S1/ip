package pika.task;

import java.util.ArrayList;

/**
 * Base Task class.
 */
public class Task { //Base Task Class
    protected boolean isDone;
    private final String name;
    private ArrayList<Tag> tags;

    /**
     * Constructor of Task class.
     *
     * @param name Name of Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.tags = new ArrayList<>(30);
    }

    /**
     * Marks the task as done when called and return it.
     *
     * @return the task's completed message.
     */
    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Checks if the task is already completed.
     *
     * @return if the task has been completed.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the string to be written in the txt file.
     *
     * @return txt format of the task for storage.
     */
    public String write() {
        if (this.isDone) {
            return "| 1 | " + this.name;
        } else {
            return "| 0 | " + this.name;
        }
    }

    /**
     * Returns the name of the task.
     *
     * @return name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds the tag to the task.
     *
     * @param tagName Name of the tag to be added.
     * @return the tag added.
     */
    public String addTag(String tagName) {
        assert tagName != null : "Pika Pi, this is not valid!";
        Tag t = new Tag(tagName);
        tags.add(t);
        return t.toString();
    }

    /**
     * Adds an arrayList of tag to the task when loading.
     *
     * @param tags Arraylist of tags to be added.
     */
    public void addTag(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getTags() {
        StringBuilder sb = new StringBuilder();
        if (tags.isEmpty()) {
            return "";
        } else {
            sb.append(" Tags :");
            for (Tag t : tags) {
                sb.append(" ");
                sb.append(t.toString());
            }
        }
        return sb.toString();
    }

    /**
     * Returns the task as String.
     *
     * @return list format of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
