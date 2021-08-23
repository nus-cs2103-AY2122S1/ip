package bern.model;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean findWord(String word) {
        String[] words = description.split("\\s+");
        for (String s : words) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
