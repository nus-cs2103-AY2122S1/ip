import java.util.Scanner;

public class Todo extends Task{

    private String taskType = "Todo";
    private String description;
    private boolean isDone;

    public Todo(String description, boolean isDone) {
        super(description, isDone);
        this.description = description;
        this.isDone = isDone;
    }

    public String getTaskType() {
        return taskType;
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        String typeIcon = getTypeIcon();
        String statusIcon = getStatusIcon();
        return typeIcon
                + " "
                + statusIcon
                + this.description;
    }
}
