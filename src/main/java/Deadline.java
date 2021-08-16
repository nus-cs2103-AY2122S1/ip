public class Deadline extends Task{
    String deadline;

    public Deadline(String name) {
        super(name.substring(0, name.indexOf(" /by ") + 1));
        this.deadline = name.substring(name.indexOf(" /by ") + 5);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }
}
