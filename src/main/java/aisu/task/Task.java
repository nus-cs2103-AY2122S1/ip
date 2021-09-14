package aisu.task;

import aisu.exception.AisuException;
import aisu.taglist.TagList;

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
    protected TagList tagsList = new TagList(); // allow for flexible tagging (any tag name is accepted)

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
     * @param tagName The tag to be added.
     */
    public void addTag(String tagName) {
        // check if tag exists.
        if (!tagName.isEmpty() && !tagsList.contains(tagName)) {
            tagsList.add(tagName);
        }
    }

    /**
     * Returns all the tags.
     * @return The tags of the task.
     */
    public TagList getTags() {
        return this.tagsList;
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
