package aisu.task;

import java.util.ArrayList;
import java.util.List;

import aisu.exception.AisuException;

/**
 * The Task class.
 * A Task contains a description and a status of being done or not.
 * There are three types of tasks: Todo, Deadline, Event.
 *
 * @author Liaw Xin Yan
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tagsList = new ArrayList<>(); // allow for flexible tagging (any tag name is accepted)

    /**
     * Constructor to initialize the Task with a description.
     * @param description A writeup of the task details.
     * @throws AisuException if there is no description.
     */
    public Task(String description) throws AisuException {
        if (description.isEmpty()) {
            throw new AisuException("The description cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the icon that represents whether the task is done or not.
     *
     * @return The icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[x]" : "[ ]");
    }

    /**
     * Marks task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Add a tag to the task.
     * @param tag The tag to be added.
     */
    public void addTag(String tag) {
        // check if tag exists.
        if (!tag.isEmpty() && !tagsList.contains(tag)) {
            tagsList.add(tag);
        }
    }

    /**
     * Removes a tag from the task.
     * @param tag The tag to be removed.
     */
    public void removeTag(String tag) {
        tagsList.remove(tag);
    }

    /**
     * Returns a string representation of all the tags.
     * @return The tags of the task.
     */
    public String getTags() {
        if (tagsList.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (String tag : tagsList) {
            result.append("#").append(tag).append(" ");
        }
        return result.toString();
    }

    /**
     * Parses data in readable format to be stored in storage.
     *
     * @return Parsed data.
     */
    public abstract String parseData();

    /**
     * Returns a string representation of the object.
     *
     * @return The Task in readable format.
     */
    public abstract String toString();
}
