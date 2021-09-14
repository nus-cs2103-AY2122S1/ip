/**
 * This class encapsulates the  Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.tasks;

import java.util.ArrayList;

public class Task {

    protected final String description;
    protected boolean isDone;
    protected ArrayList<String> eventTags = new ArrayList<>();

    /**
     * Constructor of Task objects.
     *
     * @param description The description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an 'X' if the task has been marked as done, and an " " otherwise.
     *
     * @return the string of the task to be represented in the list
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone attribute to true, which marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task completion.
     *
     * @return the string representation of the task completion
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] ";
    }

    /**
     * Returns the representation of the task to be saved in the text file.
     *
     * @return an empty string
     */
    public String getStatusString() {
        return "";
    }


    /**
     * Returns a String representation of the tags to any event.
     *
     * @return String representation of the tags to any event.
     */
    // TODO where should this fnc be
    public String getTags(ArrayList<String> eventTags) {
        StringBuilder eventTagsStr = new StringBuilder();
        if (!eventTags.isEmpty()) {
            for (String s : eventTags) {
                eventTagsStr.append(String.format(" #%s", s));
            }
            return eventTagsStr.toString();

        } else {
            return "";
        }
    }

    /**
     * Returns a String representation of the tags to any event.
     *
     * @return String representation of the tags to any event.
     */
    // TODO where should this fnc be
    public String getTagsForStorage(ArrayList<String> tags) {
        StringBuilder eventTagsStr = new StringBuilder();
        if (!tags.isEmpty()) {
            for (String s : tags) {
                eventTagsStr.append(s).append(" ");
            }
            return eventTagsStr.toString();

        } else {
            return " ";
        }
    }

    /**
     * Returns a String representation of the tags to any event.
     *
     * @param tagInfo The information associated with a tag.
     */
    public void addTag(String tagInfo) {
        this.eventTags.add(tagInfo);
    }
}

