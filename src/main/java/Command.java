public enum Command {
    DONE("done"), DELETE("delete"), TASK_TODO("todo"), TASK_DEADLINE("deadline"), TASK_EVENT("event");

    private final String label;

    Command(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
