import java.util.Scanner;

public class Todo extends Task{

    private String taskType = "Todo";
    private String description;
    private boolean isDone;

    public Todo(String description, boolean isDone) throws WrongCommandFormatException {
        super(description, isDone);
        if (description.equals("")) {
            throw new WrongCommandFormatException(
                    "No task specified. Please try again"
            );
        } else {
            this.description = description;
            this.isDone = isDone;
        }

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

    @Override
    public void markComplete() {
        if (isDone) {
            System.out.println("`" + this.description.substring(1) + "`" + " is already completed.");
        } else {
            this.isDone = true;
            System.out.println(
                    "Finally! Took you long enough to complete:" + this.description
            );
        }
    }
}
