package duke.task;

public enum TaskType {
    TODO("ToDo"),
    DEADLINE("Deadline"),
    EVENT("Event");

    private final String label;

    private TaskType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
