public class Event extends Task {
    public Event(String description) throws DukeException {
        super(description);
        if(description.strip().equals("")) {
            throw new DukeException("Your Event cannot be empty :(");
        }
        int index = description.indexOf("/at");
        this.description = description.substring(0, index) + "(at:" + description.substring(index + 3) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
