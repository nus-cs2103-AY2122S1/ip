package duke.task;

/**
 * Represents tags used to tag a task.
 */
public class TaskTag {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private static final String TAG_SYMBOL = "#";
    private String taskTag;
    /**
     * Class constructor.
     * @param taskTag the tags use to tag the task.
     */
    public TaskTag(String taskTag) throws StringIndexOutOfBoundsException {
        if (taskTag.equals("")) {
            this.taskTag = "";
        } else if (taskTag.indexOf(TAG_SYMBOL) == 0) {
            this.taskTag = taskTag;
        } else {
            throw new StringIndexOutOfBoundsException();
        }
    }
    public String getTag() {
        return this.taskTag;
    }
    public static String getTagSymbol() {
        return TAG_SYMBOL;
    }
    public String getTagInStoreFormat() {
        if (!taskTag.equals("")) {
            return " | " + taskTag;
        }
        return taskTag;
    }
}
