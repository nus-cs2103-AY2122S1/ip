package duke;

public class Deadlines extends Task {
    private String description;
    private String time;

    public Deadlines(String description, String time) {
        super(description);
        this.description = description;
        this.time = time;
    }

    public String getTaskIcon() {
        return "D";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadlines)) {
            return false;
        }
        Deadlines e = (Deadlines) o;
        return this.description.equals(e.description) && this.time.equals(e.time);
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description + "//" + time;
    }
}
