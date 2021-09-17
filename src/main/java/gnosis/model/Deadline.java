package gnosis.model;

import java.time.LocalDateTime;

import gnosis.util.DateTimeHelper;

/**
 * This class encapsulates a
 * deadline task with a specified deadline.
 *
 * @author Pawandeep Singh
 *
 * */
public class Deadline extends Task {


    /**
     * Deadline constructor to initialise deadline task.
     * Deadline initialised with deadline task name and datetime.
     *
     * @param task Deadline task name.
     * @param deadline Datetime Task deadline date/time.
     */
    public Deadline(String task, LocalDateTime deadline) {
        super(task, TaskType.DEADLINE, deadline);
    }

    /**
     * Deadline constructor to initialise deadline task.
     * Deadline initialised with deadline task name, datetime,
     * and whether task is done or not.
     *
     * @param task Deadline task name.
     * @param isTaskDone Marks whether task is done or not.
     * @param deadline Deadline Task deadline date/time.
     */
    public Deadline(String task, Boolean isTaskDone, LocalDateTime deadline) {
        super(task, TaskType.DEADLINE, deadline, isTaskDone);
    }


    /**
     * Retrieves string representative of deadline task.
     *
     * @return String representative of deadline task.
     */
    @Override
    public String toString() {
        return super.toString()
                + "(by:" + DateTimeHelper.dateToString(this.getDatetime()) + ")";
    }
}
