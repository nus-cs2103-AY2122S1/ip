public class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getStatusIcon() {
        return (completed ? "X" : " "); // mark done task with X
    }

    public String markCompleted() {
        this.completed = true;
        return "Wow you finally did something productive!\n" + this.printTask() + "\n";
    }

    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
