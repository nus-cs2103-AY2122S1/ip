public abstract class Task {
    private String description;
    private char symbol;
    private boolean isDone;

    public Task(String description, char symbol) {
        this.description = description;
        this.symbol = symbol;
        this.isDone = false;
    }

    protected String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", symbol, getStatusIcon(), description);
    }
}
