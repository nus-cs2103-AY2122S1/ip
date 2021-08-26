public class Event extends Task {

    public Event(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    @Override
    public String toFileData() {
        return "E," + super.toFileData();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), super.getDateString());
    }
}
