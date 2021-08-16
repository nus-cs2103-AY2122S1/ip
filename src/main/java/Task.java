public class Task {
    private String description;
    private boolean done = false;
    private int id;

    public Task(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public void markDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done: \n[X] " + this.description);
    }

    @Override
    public String toString() {
        String doneIndicator = this.done
                ? "[X]"
                : "[ ]";
        return (
                (this.id + 1) + ". " + doneIndicator + " " + this.description
                );
    }
}
