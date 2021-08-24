/**
 * This class encapsulates a
 * event task with a specified schedule.
 *
 * @author Pawandeep Singh
 * @version Level-4
 *
 * */
public class Event extends Task{

    public Event(String task, String schedule) {
        super(task, TaskType.EVENT, schedule);
    }

    @Override
    public String toString() {
        return super.toString() + "(at:" + this.getDatetime()  + ")";
    }
}
