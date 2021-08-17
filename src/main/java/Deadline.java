public class Deadline extends Task {
    private String name;
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.name = name;
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }
}