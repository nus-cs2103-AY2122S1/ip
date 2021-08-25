public class ToDo extends Task {
    private String type = "Todo";

    public static ToDo of(String taskSummary) {
        return new ToDo(taskSummary);
    }

    public ToDo(String taskSummary) {
        super(taskSummary);
        this.type = "Todo";
    }

    private String taskTypeSymbol() { return Character.toString(this.type.charAt(0)); }
    public static String syntax() { return "todo command syntax: \'todo <task>\'"; }

    @Override
    public String toStorageFormat() {
        return String.format("%s | %d | %s",this.taskTypeSymbol(), this.isCompleted() ? 1 : 0,this.getTaskSummary());
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s",
            this.taskTypeSymbol(),
            this.isCompleted() ? "X" : "",
            this.getTaskSummary()
        );
    }
}
