package tasklist;

public class TodoTask extends Task {
    private static String TASK_TYPE = "todo";

    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public static TodoTask createTask(String description) {
        return new TodoTask(description, false);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public static TodoTask createTaskFromStoredString(String description) {
        String statusIcon = description.substring(1, 2);
        boolean isDone = false;
        if (statusIcon.equals(STATUS_ICON_DONE)) {
            isDone = true;
        }

        String actionDescription = description.substring(3).trim();

        return new TodoTask(actionDescription, isDone);
    }
}
