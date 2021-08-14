public class DukeEvent extends DukeTask {
    final String date;

    DukeEvent(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s (at %s)", super.toString(), date);
    }
}
