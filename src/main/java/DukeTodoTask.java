public class DukeTodoTask extends DukeTask {
    private static String TASK_TYPE = "todo";

    private DukeTodoTask(String description) {
        super(description);
    }

    public static DukeTodoTask createTask(String description) {
        return new DukeTodoTask(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
