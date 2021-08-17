public class Task {
    private boolean completed;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    @Override
    public String toString() {
        String check = this.completed ? "[X] " : "[ ] ";
        return check + taskName;
    }

    public void markAsDone() {
        this.completed = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t " + this + "\n");
    }

}
