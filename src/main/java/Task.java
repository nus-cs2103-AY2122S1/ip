public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public String saveTaskToFile() {
        return typeOfTask() + "||" + getStatusIcon() + "||" + this.getDescription();
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.typeOfTask(),this.getStatusIcon(), this.getDescription());
    }
}
