public class ToDo extends Task {
    private String type;

    public static ToDo of(String taskSummary) {
        return new ToDo(taskSummary);
    }

    public ToDo(String taskSummary) {
        super(taskSummary);
        this.type = "Todo";
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s",
            this.type.charAt(0),
            this.isCompleted() ? "X" : "",
            this.getTaskSummary()
        );
    }
}
