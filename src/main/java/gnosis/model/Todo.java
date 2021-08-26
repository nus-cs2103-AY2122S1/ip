package gnosis.model;

import java.time.LocalDateTime;

/**
 *
 * This class encapsulates a t0do task.
 *
 * @author Pawandeep Singh
 * */
public class Todo extends Task {


    /**
     * T0do constructor to initialise t0do task.
     * T0do initialised with task name.
     *
     * @param task t0do task name.
     */
    public Todo(String task) {
        super(task, TaskType.TODO);
    }

    /**
     * T0do constructor to initialise t0do task.
     * T0do initialised with task name, date/time of when t0do created,
     * and whether task is done or not.
     *
     * @param task t0do task name.
     * @param isTaskDone Marks whether t0do is done or not.
     */
    public Todo(String task, Boolean isTaskDone) {
        super(task, TaskType.TODO, LocalDateTime.now(), isTaskDone);
    }

    /**
     * Retrieves string representative of t0do task.
     *
     * @return String representative of t0do task.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
