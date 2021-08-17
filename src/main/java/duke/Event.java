package duke;

public class Event extends Item {
    private String time;

    public Event(String[] strings) {
        super(strings);
        String line = String.join(" ", strings);
        int idx = line.indexOf("/at");
        this.setName(line.substring(6, idx));
        this.time = line.substring(idx + 4, line.length());
    }

    @Override
    public void checkInput(String[] strings) {
        super.checkInput(strings);
        String line = String.join(" ", strings);
        int idx = line.indexOf("/at");
        if (idx == -1) {
            throw new DukeException("please specify /at.");
        } else if (idx == line.length() - 3) {
            throw new DukeException("by when?");
        } else if (idx == 6) {
            throw new DukeException("what is the item?");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
