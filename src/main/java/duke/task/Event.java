package duke.task;

public class Event extends Task{
    String date;

    public Event(String desc, String date) {
        super(desc);
        this.date= date;

    }

    public Event(String isDone, String desc, String date) {
        super(isDone,desc);
        this.date= date;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "+date+")";
    }

    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + date;
    }
}
