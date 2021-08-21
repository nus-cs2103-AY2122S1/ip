public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event
     * @param input the input array consisting of description and date/time
     */
    public Event(String[] input) throws EmptyDescriptionException, EmptyTimeException {
        super(input[0]);
        if (input.length < 2) throw new EmptyTimeException();
        this.at = input[1];
    }
    
    public Event(String[] input, boolean isDone) {
        super(input[0], isDone);
        this.at = input[1];
    }
    
    @Override
    public String convertToData() {
        return String.format("E/%s/%s/%s", this.isDone ? "1" : "0", this.description, this.at);
    }

    /**
     * Returns string representation of Event
     * @return string representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

}
