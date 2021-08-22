public class Deadline extends Task{
    String deadline;

    public Deadline(String name) throws DukeDeadlineException {
        super(name.substring(0, name.indexOf(" /by ") + 1));
        this.deadline = name.substring(name.indexOf(" /by ") + 5);
        if (name.equals("")) {
            throw  new DukeDeadlineException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }

    @Override
    public String toDataString() {
        return "D|" + super.toDataString() + "|" + this.deadline;
    }
}
