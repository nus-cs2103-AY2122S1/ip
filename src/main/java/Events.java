public class Events extends Task {

    private String dateTimeAt;
    public Events(String description, String dateTimeAt) {
        super(description);
        this.dateTimeAt = dateTimeAt;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(%s:%s)", this.dateTimeAt.substring(0,2), this.dateTimeAt.substring(2));
    }
}
