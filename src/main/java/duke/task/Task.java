package duke.task;

import java.util.ArrayList;

/**
 * Represents a Task that the user have added to the task list.
 */
public class Task {
    public static final int MAX_TAGS = 5;
    private String name;
    private boolean isComplete;
    private String taskType = "G";
    private ArrayList<String> tags;

    /**
     * Creates a new Task object.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        assert !name.contains("|");
        this.name = name;
        this.isComplete = false;
        this.tags = new ArrayList<String>(MAX_TAGS);
    }

    /**
     * Creates a new Task object.
     * Allows specifying the TaskType
     *
     * @param name Name of the task.
     * @param taskType String representing the type of the task.
     */
    public Task(String name, String taskType) {
        assert !name.contains("|");
        this.name = name;
        this.isComplete = false;
        this.taskType = taskType;
        this.tags = new ArrayList<String>(MAX_TAGS);
    }

    /**
     * Creates a new Task object.
     * Allows specifying the TaskType, and whether it is completed.
     *
     * @param name Name of the task.
     * @param isComplete Boolean representing whether task is completed.
     * @param taskType String representing the type of the task.
     */
    public Task(String name, boolean isComplete, String taskType, String[] tags) {
        assert !name.contains("|");
        this.name = name;
        this.isComplete = isComplete;
        this.taskType = taskType;
        this.tags = new ArrayList<String>(MAX_TAGS);
        for (int i = 0; i < tags.length; i++) {
            if (i == MAX_TAGS) {
                break;
            } else if (tags[i] == null) {
                break;
            } else if (tags[i].equals("")) {
                break;
            }
            this.tags.add(tags[i]);
        }
    }

    /**
     * Sets the task to be completed.
     *
     * @return True.
     */
    public boolean completeTask() {
        this.isComplete = true;
        return true;
    }

    /**
     * Returns the name attribute.
     *
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if task is completed, false otherwise.
     */
    public boolean hasCompleted() {
        return isComplete;
    }

    /**
     * Returns the taskType of the task.
     *
     * @return String representing the task type.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns the string to be used for saving the task on a file.
     *
     * @return String representing the Task.
     */
    public String getSaveFormat() {
        return String.format("%s|%s|%s|%s", taskType, isComplete ? "c" : "i", name, formatTags(tags));
    }

    public String getTags() {
        return formatTags(tags);
    }

    public String addTag(String tag) {
        if (tags.size() == MAX_TAGS) {
            return String.format("Task \"%s\" already have %s tags, delete a tag first.", name, MAX_TAGS);
        }
        if (tags.contains(tag)) {
            return String.format("Task \"%s\" already has tag [%s]", name, tag);
        }
        assert tags.size() < MAX_TAGS;

        tags.add(tag);
        return String.format("Task: %s\nTags: %s", name, getTags());
    }

    public String deleteTag(String tag) {
        if (!tags.remove(tag)) {
            return String.format("Task \"%s\" doesn't have tag [%s]\nTags: %s", name, tag, getTags());
        }

        return String.format("Task: %s\nTags: %s", name, getTags());
    }

    private static String formatTags(ArrayList<String> tags) {
        String result = "";
        for (int i = 0; i < tags.size(); i++) {
            result += tags.get(i) + ", ";
        }
        if (result.length() == 0) {
            return result;
        }

        return result.substring(0, result.length() - 2);
    }
}
