package gnosis.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a
 * deadline gnosis.task with a specified deadline.
 *
 * @author Pawandeep Singh
 * @version Level-4
 *
 * */
public class Deadline extends Task{

    public Deadline(String task, LocalDateTime deadline) {
        super(task, TaskType.DEADLINE, deadline);
    }

    public Deadline(String task, Boolean isTaskDone, LocalDateTime deadline) {
        super(task, TaskType.DEADLINE, deadline, isTaskDone);
    }

    @Override
    public String toString() {
        return super.toString()
                + "(by:" + this.getDatetime().format(
                        DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma")) + ")";
    }
}
