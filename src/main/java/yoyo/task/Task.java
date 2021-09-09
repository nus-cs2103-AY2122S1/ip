package yoyo.task;

import static yoyo.utility.Constant.COMMA_SEPARATOR;

import java.util.ArrayList;

/**
 * Abstract class representing a task in Yoyo app.
 */
public abstract class Task {
    protected boolean isDone = false;
    protected String name;
    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructor for creating an instance of Task. Must specify name.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor for creating an instance of Task. Must specify name, isDone.
     *
     * @param name   Name of the task.
     * @param isDone Boolean indicating if task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Constructor for creating an instance of Task. Must specify name, isDone, tags.
     *
     * @param name   Name of the task.
     * @param isDone Boolean indicating if task is done.
     * @param tags   Array of tags belonging to this task.
     */
    public Task(String name, boolean isDone, String[] tags) {
        this.name = name;
        this.isDone = isDone;
        for (int i = 0; i < tags.length; i++) {
            this.addTag(tags[i]);
        }
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    public abstract String printType();

    /**
     * Produces a string containing task's status.
     *
     * @return a string containing task's status.
     */
    public abstract String showStatus();

    /**
     * Produces a string containing task's status in write format.
     *
     * @return a string containing task's status in write format.
     */
    public abstract String showStatusWrite();

    /**
     * Adds a new tag to this task.
     *
     * @param tagName Name of tag to be added.
     */
    public void addTag(String tagName) {
        if (tagName.trim().length() == 0) {
            return;
        }
        tags.add(tagName);
    }

    /**
     * Returns string displaying all tags belonging to this task
     *
     * @return String about tags in #tag1 #tag2 ...) format.
     */
    public String showTags() {
        String result = "";
        if (tags.size() != 0) {
            int length = tags.size();
            for (int i = 0; i < length - 1; i++) {
                String toAdd = "#" + tags.get(i) + " ";
                result += toAdd;
            }
            result += "#" + tags.get(length - 1);
        }
        return result;
    }

    /**
     * Returns string displaying all tags belonging to this task in write format.
     *
     * @return String about tags in ", tag1, tag2 ..." format.
     */
    public String showTagsWriteFormat() {
        int length = tags.size();
        if (length == 0) {
            return "";
        }
        String result = COMMA_SEPARATOR;
        for (int i = 0; i < length - 1; i++) {
            String toAdd = tags.get(i) + COMMA_SEPARATOR;
            result += toAdd;
        }
        result += tags.get(length - 1);
        return result;
    }

    /**
     * Checks if name contains input string.
     *
     * @param str Input string.
     * @return True if name contains input string, false otherwise.
     */
    public boolean containsString(String str) {
        return this.name.contains(str);
    }

    /**
     * Returns a status string indicating state of completion.
     *
     * @return A status string for the task.
     */
    public String printCompletionStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    /**
     * Toggles completion status.
     */
    public void toggleDone() {
        this.isDone = true;
    }

    /**
     * Defines how to compare two Task instances.
     *
     * @param o Object to be compared with.
     * @return A boolean.
     */
    @Override
    public boolean equals(Object o) {
        //Test code should be passing the right argument into this method
        assert o instanceof Task;

        @SuppressWarnings("unchecked")
        Task otherTask = (Task) o;
        return this.isDone == otherTask.isDone
                && this.name.equals(otherTask.name);
    }

}
