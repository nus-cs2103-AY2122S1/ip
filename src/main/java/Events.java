public class Events extends Task {
    protected String at;

    public Events(String description, String at) {
        super(description, Instruction.EVENT);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
