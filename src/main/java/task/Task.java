package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Tag;

/**
 * Task is the superclass of classes to be stored in the database.
 * Subclasses require a description field and have an isDone field that indicates completion status.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected List<Tag> tags;

    /**
     * Constructor of superclass Task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<Tag>();
    }

    /**
     * Gets the status of the current task object.
     *
     * @return the string status where X means done and blank is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done by changing the isDone boolean to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the description of this task.
     *
     * @return the string description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the boolean value of this object's isDone status.
     *
     * @return boolean of this object's isDone.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    public String getTags() {
        if (tags.isEmpty()) {
            return "None";
        }

        List<String> tagNameList = this.tags.stream().map(tag -> {
            return tag.getTagName();
        }).collect(Collectors.toList());

        String tags = String.join(" ", tagNameList);
        return tags;
    }


    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Retrieves the description of this object's status.
     *
     * @return string description of the object's status.
     */
    @Override
    public String toString() {
        String taskTags = "tags: ";

        if (!this.tags.isEmpty()) {
            String tags = this.tags.stream()
                    .map(tag -> {
                        return tag.toString() + " ";
                    }).collect(Collectors.joining());

            taskTags += tags;
        } else {
            taskTags += "None";
        }

        String message = String.format("[%s] %s\n          %s\n",
                this.getStatusIcon(), this.description, taskTags);
        return message;
    }
}
