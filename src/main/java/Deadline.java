public class Deadline extends Task {

    String dline;

    public Deadline(String input) {
        super(input.substring(0, input.indexOf("/by ") - 1));
        this.dline = input.substring(input.indexOf("/by " + 4));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dline + ")";
    }
}
