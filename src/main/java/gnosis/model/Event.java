package gnosis.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a
 * event gnosis.task with a specified schedule.
 *
 * @author Pawandeep Singh
 * @version Level-4
 *
 * */
public class Event extends Task{

    public Event(String task, LocalDateTime schedule) {
        super(task, TaskType.EVENT, schedule);
    }

    public Event(String task, Boolean isTaskDone, LocalDateTime schedule) {
        super(task, TaskType.EVENT, schedule, isTaskDone);
    }

    @Override
    public String toString() {
        return super.toString()
                + "(at:" + this.getDatetime().format(
                        DateTimeFormatter.ofPattern("MM dd yyyy, hh:mma")) + ")";
    }
}
