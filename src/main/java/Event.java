public class Event extends Task{

    protected String returnDate;

    public Event(String description, String returnDate) {
        super(description);
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), this.returnDate);
    }
}
