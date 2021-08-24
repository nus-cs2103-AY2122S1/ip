package duke;

public class Events extends Task {
    String type;
    String time;

    public Events(String title, String time) {
        super(title);
        this.type = "E";
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + "(at:" + time + ")";
    }

    @Override
    public String writeTask() {
        return type + " | " + super.writeTask() + " | " + time;
    }
}