public class Deadline extends Task{

    private String deadline;

    public Deadline(String task) {
        super(task.split(" /")[0]);
        this.deadline = task .split(" /")[1].substring(3);
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
