public class Event extends Task {
    private final String date;

    Event(String body, String date) {
        super(body, false);
        this.date = date;
    }
    Event(String body, boolean done, String date) {
        super(body, done);
        this.date = date;
    }

    @Override
    Task setDone() {
        return new Event(this.getBody(), true, this.date);
    }

    @Override
    public String toString() {
        if (this.getDone()) {
            return "[E] [X]" + this.getBody() + " (at:" + this.date + ")";
        }
        else {
            return "[E] [ ]" + this.getBody() + " (at:" + this.date + ")";
        }
    }
}
