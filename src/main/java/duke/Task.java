package duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Represents an element in the <code>TaskList</code>.
 * Split into 3 types: <code>Todo, Deadline, Event</code>.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected Category category;
    protected ArrayList<String> tags;

    public enum Category {
        TODO, DEADLINE, EVENT
    }

    /**
     * Returns a Task object.
     *
     * @param description description of Task
     * @param isDone      indicates if Task has been completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new ArrayList<>();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    /**
     * Returns tags.
     *
     * @return String of tags formatted with '#' signs
     */
    public String printTags() {
        if (this.tags.isEmpty()) {
            return "";
        }

        String tagsList = this.tags
            .stream()
            .map(tag -> "#" + tag)
            .collect(Collectors.joining(" "));
        return tagsList;
    }

    /**
     * Adds tag to the task after converting to lower case and checking that it does not exist.
     *
     * @param newTag to be added
     */
    public void addTag(String newTag) {
        String tagToAdd = newTag.toLowerCase();

        if (!this.tags.contains(tagToAdd)) {
            this.tags.add(tagToAdd);
            Collections.sort(this.tags);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " " + this.printTags();
    }
}
