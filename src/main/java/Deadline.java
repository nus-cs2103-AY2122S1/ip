public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description, Task.Type.D);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(Task.Type.D, isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }

    public String toFileString() {return super.toFileString() + " " + deadline;}
}
