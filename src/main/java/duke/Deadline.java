package duke;

public class Deadline extends Item {
    private String time;

    public Deadline(String[] strings) {
        super(strings);
        String line = String.join(" ", strings);
        int idx = line.indexOf("/by");
        this.setName(line.substring(9, idx));
        this.time = line.substring(idx + 4, line.length());
    }

    @Override
    public void checkInput(String[] strings) {
        super.checkInput(strings);
        String line = String.join(" ", strings);
        int idx = line.indexOf("/by");
        if (idx == -1) {
            throw new DukeException("please specify /by.");
        } else if (idx == line.length() - 3) {
            throw new DukeException("by when?");
        } else if (idx == 9) {
            throw new DukeException("what is the item?");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
