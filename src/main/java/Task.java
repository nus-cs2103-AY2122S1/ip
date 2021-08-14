class Task {
    public boolean isDone = false;
    private final String description;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }
}