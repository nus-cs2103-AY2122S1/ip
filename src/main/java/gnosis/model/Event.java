package gnosis.model;

import java.time.LocalDateTime;

import gnosis.util.DateTimeHelper;

/**
 * This class encapsulates an
 * event task with a specified schedule.
 *
 * @author Pawandeep Singh
 *
 *
 * */
public class Event extends Task {

    /**
     * Event constructor to initialise event task.
     * Event initialised with task name and schedule datetime.
     *
     * @param task Task name.
     * @param schedule Event schedule date/time.
     */
    public Event(String task, LocalDateTime schedule) {
        super(task, TaskType.EVENT, schedule);
    }

    /**
     * Event constructor to initialise event task.
     * Event initialised with event task name, schedule date/time,
     * and whether event is done or not.
     *
     * @param task Event task name.
     * @param isTaskDone Marks whether task is done or not.
     * @param schedule Event task Schedule.
     */
    public Event(String task, Boolean isTaskDone, LocalDateTime schedule) {
        super(task, TaskType.EVENT, schedule, isTaskDone);
    }

    /**
     * Retrieves string representative of event task.
     *
     * @return String representative of event task.
     */
    @Override
    public String toString() {
        return super.toString()
                + "(at:" + DateTimeHelper.dateToString(this.getDatetime()) + ")";
    }
}
