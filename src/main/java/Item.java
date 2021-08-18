class Task {

    private final String taskName;
    private final boolean isCompleted;

    Task(String itemName) {
        this.taskName = itemName;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X": " ") + "] " + this.taskName;
    }
}
