public class Deadlines extends Task { //we represent the deadline as a String
    protected String time;

    public Deadlines(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "] [" + this.getStatusIcon() + "] " + description + " (" + time + ")";
    }
}
