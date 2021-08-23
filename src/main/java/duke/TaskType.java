package duke;

public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private final String taskIcon;
    private TaskType(String taskIcon) {
        this.taskIcon = taskIcon;
    }

    public String getTaskIcon() {
        return this.taskIcon;
    }

    public static TaskType identifyTask(Task task) {
        return task instanceof Todo
            ? TaskType.TODO
            : task instanceof Deadline
            ? TaskType.DEADLINE
            : task instanceof Event
            ? TaskType.EVENT
            : null;
    }

    public static TaskType convertTaskIcon(String taskIcon) {
        switch (taskIcon) {
        case "D":
            return TaskType.DEADLINE;
        case "E":
            return TaskType.EVENT;
        case "T":
            return TaskType.TODO;
        default:
            throw new UnsupportedOperationException("unknown task icon");
        }
    }
};
