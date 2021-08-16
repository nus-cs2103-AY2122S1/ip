public class Deadline extends Task {
    private String date;
    public Deadline(String input) {
        super(input.split("/by")[0]);
        this.date = input.split("/by")[1];
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + String.format("(by:%s)", this.date));
    }
}
