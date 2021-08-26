public class Deadline extends Task{
    String time;

    public Deadline(String description, String time) {
        super(description, "[D]", false);
        this.time = time;
    }

    public Deadline(String description, String time, boolean status) {
        super(description, "[D]", status);
        this.time = time;
    }

    public String getformmatedData() {
        String formmatedStatus = super.isDone() ? "1|" : "0|";
        return "D|" + formmatedStatus + super.getDescription() + "|" + time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time + ")";
    }
}
