package daisy.task;

/**
 * Task class that handles the common traits of the different types of tasks.
 */
public class Task {

    static final String TASK_STRING_FORMAT = "[%s] %s";
    static final String TASK_FILE_FORMAT = "%d // %s";
    static final String DONE_STATUS_ICON = "X";
    protected String description;
    protected boolean isDone;
    private TagList tagList;

    /**
     * Constructs the task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagList = new TagList();
    }

    /**
     * Returns the status icon of the task.
     *
     * @return String status icon.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_STATUS_ICON : " "); // mark done task with X
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the status of the task.
     *
     * @param done Integer representation of status.
     */
    public void setDoneStatus(int done) {
        isDone = done == 1;
    }

    /**
     * Checks whether description contains query string.
     *
     * @param query Query string to check for in description.
     * @return Whether description contains query string.
     */
    public boolean containsQuery(String query) {
        assert query != null : "Query to check is null";
        return description.contains(query);
    }

    @Override
    public String toString() {
        return String.format(TASK_STRING_FORMAT, getStatusIcon(), description);
    }

    /**
     * Returns the String representation of task in file format.
     * File format is the format used to save tasks in a file.
     *
     * @return String representation of task in file format.
     */
    public String convertToFileFormat() {
        return String.format(TASK_FILE_FORMAT, isDone ? 1 : 0, description);
    }

    public void addTags(String ... tagStrings) {
        tagList.addTags(tagStrings);
    }

    public String getTagsString() {
        return tagList.toString();
    }

    public String getTagsFileFormat() {
        return tagList.convertToFileFormat();
    }
}
