public class Deadline extends Task{
    String deadline;

    public Deadline(String name) throws DukeDeadlineException {
        super(name.substring(0, name.indexOf(" /by ") + 1));
        this.deadline = name.substring(name.indexOf(" /by ") + 5);
        if (name.equals("")) {
            throw  new DukeDeadlineException();
        }
    }

    public Deadline(String[] input) {
        super(input[2].substring(0, input[1].length() - 1), input[1].equals("T") ? true : false);
        this.deadline = input[3];
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
