package tipsy;

public enum TaskType {
    TODO, DEADLINE, EVENT;

    public static TaskType getTaskType(String taskTypeString) {
        switch (taskTypeString.toLowerCase()) {
        case "todo":
            return TaskType.TODO;
        case "deadline":
            return TaskType.DEADLINE;
        case "event":
            return TaskType.EVENT;
        default:
            assert false; // Invalid input
            return null;
        }
    }
}
