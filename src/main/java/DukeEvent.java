public class DukeEvent extends DukeTask {
    final String date;

    DukeEvent(String name, String date) {
        super(name);
        this.date = date;
    }

    public DukeEvent(String name, boolean isDone, String date) {
        super(name, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s (at %s)", super.toString(), date);
    }

    @Override
    public String toSerializedString() {
        return String.format("%s/%d/at/%s", name, isDone ? 1 : 0, date);
    }
}
