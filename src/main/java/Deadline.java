public class Deadline extends Task{

    protected String returnDate;

    public Deadline(String description, String returnDate, Boolean empty) throws DukeException {
        super(description);
        this.returnDate = returnDate;

        if(empty) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), this.returnDate);
    }
}
