public class Deadline extends Task {

    public Deadline() {}
    public Deadline(String desc) {
        super(desc);
    }
    public Deadline(String desc, String time) throws DukeException {
        super(desc);
        addTime(time);
    }
    public Deadline(String desc, String dl, boolean done) {
        super(desc, done);
        // this.dl = dl;
        addTime(dl);
    }

    public void addTime(String time) {
        super.addTime(time);
    }
    
    public String toDB() {
        // return String.format("D | %d | %s | %s", super.done ? 1 : 0, super.desc, dl);
        return String.format("D | %d | %s | %s | %s", super.done ? 1 : 0, super.desc, dateTime.format(Task.formatter),
                details);
    }

    @Override
    public String toString() {
        String timeSeq = formatTime();
        return "[D]" + super.toString() + (timeSeq != null ? String.format(" (by: %s)", timeSeq) : "");
    }
}
