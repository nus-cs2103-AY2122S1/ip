public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String checkbox = this.isDone ? "[X] " : "[ ] ";
        return checkbox + this.description;
    }
}