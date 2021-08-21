package entity.list;

public class DukeTodoTask extends DukeTask {
    private static String TASK_TYPE = "todo";

    private DukeTodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public static DukeTodoTask createTask(String description) {
        return new DukeTodoTask(description, false);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public static DukeTodoTask createTaskFromStoredString(String description) {
        String statusIcon = description.substring(1, 2);
        boolean isDone = false;
        if (statusIcon.equals(DukeTask.STATUS_ICON_DONE)) {
            isDone = true;
        }

        String actionDescription = description.substring(3).trim();

        return new DukeTodoTask(actionDescription, isDone);
    }
}
