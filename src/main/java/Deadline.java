public class Deadline extends Task{
    String time;

    public Deadline(String description, String time) {
        super(description, "[D]");
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time + ")";
    }
}
