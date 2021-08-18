public class Task {
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("%s", taskName);
    }

}
