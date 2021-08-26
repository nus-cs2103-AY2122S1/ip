package duke;

public class Event extends Task {
    public String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTaskIcon() {
        return "E";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event e = (Event) o;
        return this.description.equals(e.description) && this.time.equals(e.time);
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description + "//" + time;
    }
}
