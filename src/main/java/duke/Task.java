package duke;

import java.util.ArrayList;

/**
 * Encapsulates the abstract task class that concrete task implementations extend from.
 */
public abstract class Task {
    private final String description;
    private Boolean completionStatus;
    private ArrayList<String> tags;

    /**
     * Constructs a Task object. Only for use with extended classes.
     *
     * @param description Task description.
     * @param isCompleted Completion status of task.
     */
    public Task(String description, Boolean isCompleted, ArrayList<String> tags) {
        this.description = description;
        this.completionStatus = isCompleted;
        this.tags = tags;
    }

    public void completeTask() {
        completionStatus = true;
    }

    /**
     * Returns a String icon depending on whether the task is complete or not.
     *
     * @return The String icon.
     */
    public String completionIcon() {
        return completionStatus ? "[X]" : "[-]";
    }

    /**
     * Returns a String icon depending on what type the task is.
     *
     * @return The String icon.
     */
    public abstract String typeIcon();

    /**
     * Returns a String consisting of the tags attached to the current task.
     *
     * @return The String of tags.
     */
    public String getTags() {
        if (tags.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder(" (Tags:");
        for (String t : tags) {
            result.append(" ").append(t);
        }
        result.append(")");
        return result.toString();
    }

    /**
     * Adds a string to tags ArrayList.
     *
     * @param s Tag to be added.
     */
    public void addTag(String s) {
        tags.add(s);
    }

    @Override
    public String toString() {
        return typeIcon() + " " + completionIcon() + " " + description;
    }

    /**
     * Returns a String to be written to the output file.
     *
     * @return The String.
     */
    public String toFileString() {
        return typeIcon() + " " + completionIcon() + " " + description;
    }
}
