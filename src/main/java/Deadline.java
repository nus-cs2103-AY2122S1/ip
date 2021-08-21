public class Deadline extends Task {

    public Deadline() {}
    public Deadline(String desc) {
        super(desc);
    }
    public Deadline(String desc, String time) throws DukeException {
        super(desc);
        addTime(time);
    }

    public void addTime(String time) {
        super.addTime(time);
    }

    @Override
    public String toString() {
        String timeSeq = formatTime();
        return "[D]" + super.toString() + (timeSeq != null ? String.format(" (by:%s)", timeSeq) : "");
    }
}
