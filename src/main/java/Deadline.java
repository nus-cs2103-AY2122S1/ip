public class Deadline extends Task{
    private String time;

    public Deadline(String name, String time) {
        super(name, "D");
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getSaveFormat() {
        return String.format("%s|%s", super.getSaveFormat(), this.time);
    }
}
