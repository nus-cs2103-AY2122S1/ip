public class Deadline extends Task{

    protected String returnDate;

    public Deadline(String description, String returnDate) {
        super(description);
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), this.returnDate);
    }
}
