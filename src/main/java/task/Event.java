package task;

public class Event extends Task {

    protected String date;

    public Event(String description, String at) {
        super(description);
        this.date = at;
    }

    @Override
    public String getDetails(){
        return super.toString() + " (at: " + date + ")";
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + date + ")";
    }
}