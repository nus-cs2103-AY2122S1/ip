public class Deadline extends Task {

    String dline;

    public Deadline(String input) {
        super(input.substring(0, input.indexOf("/by ") - 1));
        this.dline = input.substring(input.indexOf("/by ") + 4);
    }

    public Deadline(String name, String dline) {
        super(name);
        this.dline = dline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dline + ")";
    }

    public String printToFile() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.name + " | " + this.dline;
    }
}
