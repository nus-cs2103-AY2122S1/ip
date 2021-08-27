public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private final String taskType;

    private TaskType(final String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType(){
        return taskType;
    }
}
