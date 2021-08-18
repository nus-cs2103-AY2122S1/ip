public class Deadline extends Task {
    private String time;

    public Deadline(String rawTime) {
        super(rawTime.split("/", 2)[0]);
        String time = rawTime.split("/", 2)[1];
        time = "(" + time + ")";
        this.time = time;
    }

    public String getTime() {
        System.out.println(time);
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + time;
    }
}
