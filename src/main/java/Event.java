/**
 * This class encapsulates a
 * event task with a specified schedule.
 *
 * @author Pawandeep Singh
 * @version Level-4
 *
 * */
public class Event extends Task{
    private String schedule;


    public Event(String task, String schedule) {
        super(task);
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at:"+ this.schedule  +")";
    }
}
