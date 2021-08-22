package duke;

public class Deadline extends Item {
    private String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getPickle() {
        return super.getPickle() + "###" + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
