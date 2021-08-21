public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline
     * @param input the input array consisting of description and date/time
     */
    public Deadline(String[] input) throws EmptyDescriptionException, EmptyTimeException {
        super(input[0]);
        if (input.length < 2) throw new EmptyTimeException();
        this.by = input[1];
    }
    
    public Deadline(String[] input, boolean isDone) {
        super(input[0], isDone);
        this.by = input[1];
    }
    
    @Override
    public String convertToData() {
        return String.format("D/%s/%s/%s", this.isDone ? "1" : "0", this.description, this.by);
    }

    /**
     * Returns string representation of this deadline
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
