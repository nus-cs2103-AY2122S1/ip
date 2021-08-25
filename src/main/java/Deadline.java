public class Deadline extends Task {
    private String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String stringifyTask() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.time);
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.time);
    }
}
