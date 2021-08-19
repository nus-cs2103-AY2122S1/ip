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

    public String typeOfTask() {
        return " ";
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("I have marked \"" + this.description + "\" as done!");
        System.out.println(this.toString());
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.typeOfTask(),this.getStatusIcon(), this.getDescription());
    }
}
