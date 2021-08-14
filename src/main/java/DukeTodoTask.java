public class DukeTodoTask extends DukeTask {
    private static String TASK_TYPE = "todo";

    private DukeTodoTask(String description) {
        super(description);
    }

    public static DukeTodoTask createTask(String description) throws MissingTaskDescriptionException {
        // Remove the 'to-do' prefix and any whitespace
        String descriptionWithoutPrefix = description.substring(4).trim();

        // Check that description is not empty
        DukeTask.validateDescriptionNotEmpty(DukeTodoTask.TASK_TYPE, descriptionWithoutPrefix);

        return new DukeTodoTask(descriptionWithoutPrefix);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
