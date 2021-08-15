public class DukeEvent extends DukeTask {
    final DukeDate date;

    DukeEvent(String name, String date) {
        super(name);
        this.date = DukeDate.of(date);
    }

    @Override
    public String toString() {
        return String.format("%s (at %s)", super.toString(), date);
    }
}
