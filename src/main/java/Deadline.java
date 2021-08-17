public class Deadline extends Task {

    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.taskType = "D";
        this.date = date;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += String.format(" (by: %s)", this.date);
        return str;
    }
}
