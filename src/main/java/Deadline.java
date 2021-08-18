public class Deadline extends Task {
    public Deadline(String description) throws DukeException {
        super(description);
        if(description.strip().equals("")) {
            throw new DukeException("Your Deadline cannot be empty :(");
        }
        int index = description.indexOf("/by");
        this.description = description.substring(0, index) + "(by:" + description.substring(index + 3) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}