public class Deadline extends Task{

    private String deadline;

    Deadline(String name, boolean done, String deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if(this.isDone()) {
            return String.format("[D][X] %s (by: %s)", this.getName(), this.deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)", this.getName(), this.deadline);
        }
    }
}
