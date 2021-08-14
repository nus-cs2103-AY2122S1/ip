public class DukeTodoTask extends DukeTask {
    private DukeTodoTask(String description) {
        super(description);
    }

    public static DukeTodoTask createTask(String description) {
        // Remove the 'to-do' prefix and any whitespace
        return new DukeTodoTask(description.substring(4).trim());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
