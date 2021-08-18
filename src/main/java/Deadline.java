public class Deadline extends Task{

    private String by;

    public Deadline(String name) {
           super(name);
    }

    public String toString() {
        return "[D]" + super.toString();
    }
}
