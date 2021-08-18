public class Deadline extends Task{

    private String deadline;

    public Deadline(String task) {
        super(task.split(" /by ")[0]);
        this.deadline = task .split(" /by ")[1];
    }

    @Override
    public String getDescription() {
        return "[D] " + super.getDescription() + " (by: " + this.deadline + ")";
    }


    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline + ")";
    }
}
