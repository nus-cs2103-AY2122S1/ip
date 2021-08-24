public class Events extends Task{
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     *
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
