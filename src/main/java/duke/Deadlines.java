package duke;

public class Deadlines extends Task {
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
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description + "//" + time;
    }
}
