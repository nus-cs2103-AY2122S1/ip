package duke.task;

import java.util.ArrayList;

/**
 * Task store information that user wants to do.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        tags = new ArrayList<>();
    }

    /**
     * Returns the status of the task (whether is it done).
     *
     * @return Status Icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns description of the task.
     *
     * @return decription of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String fullDescription = String.format("[%s] %s", getStatusIcon(), getDescription());
        return fullDescription;
    }

    private String getTagToFileString() {
        String fileString = "";
        for (int i = 0; i < tags.size(); i++) {
            fileString = fileString + "#" + tags.get(i);
        }
        return fileString;
    }

    /**
     * Converts the task into a string that can be store in the file.
     *
     * @return the text format of the task to be store in the file.
     */
    public String toFileString() {
        String fileString = String.format("%s | %s | %s",
                getStatusIcon(), getTagToFileString(), getDescription());
        return fileString;
    }

    /**
     * Checks whether key word is present in the task's description.
     *
     * @param keyWord Key word to match the task.
     * @return Boolean whether the task match the key word.
     */
    public boolean isKeyWordPresent(String keyWord) {
        int intIndex = description.indexOf(keyWord);
        boolean isPresent = !(intIndex == -1);

        return isPresent;
    }

    /**
     * Adds tag to the task.
     *
     * @param tag Tag to be added into tags.
     */
    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Checks whether tag is present.
     *
     * @param tag Tag to search for.
     * @return
     */
    public boolean isTagPresent(String tag) {
        return tags.contains(tag);
    }

}
