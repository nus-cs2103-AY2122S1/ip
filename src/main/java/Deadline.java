public class Deadline extends Task{

    protected String returnDate;

    public Deadline(String restOfInput, Boolean empty) throws DukeException {
        super(restOfInput);

        if(empty || restOfInput.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (restOfInput.contains("/by")) {
            description = restOfInput.split("/by", 2)[0];
            returnDate = restOfInput.split("/by", 2)[1];
            if (description.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (returnDate.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The deadline of a... deadline cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The deadline of a... deadline cannot be empty.");
        }
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), this.returnDate);
    }
}
