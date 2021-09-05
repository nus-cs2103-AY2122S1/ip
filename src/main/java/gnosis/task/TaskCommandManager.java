package gnosis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import gnosis.model.Deadline;
import gnosis.model.Event;
import gnosis.model.Task;
import gnosis.model.Todo;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;


public class TaskCommandManager {

    private List<Task> tasks;

    public TaskCommandManager(List<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Adds a t0do tasks and returns the t0do.
     *
     * @param tdo The t0do input to save.
     * @return t0do The t0do that is saved.
     * @throws GnosisException If input is empty.
     */
    public Todo addTodo(String tdo) throws GnosisException {
        if (tdo.trim().equalsIgnoreCase("")) {
            throw new GnosisException(GnosisConstants.TODO_EMPTY_EXCEPT_MESSAGE);
        }

        Todo td = new Todo(tdo);
        tasks.add(td);

        return td;
    }

    /**
     * Adds an event tasks to save.
     *
     * @param event event to save.
     * @return event The event that is saved.
     * @throws GnosisException If input is empty.
     */
    public Event addEvent(String event) throws GnosisException {
        String[] splitTaskInput = event.split("/at");

        if (splitTaskInput.length != 2) {
            //event empty exception
            throw new GnosisException(GnosisConstants.EVENT_EMPTY_EXCEPT_MESSAGE);
        }

        String taskName = splitTaskInput[0];
        String taskSchedule = splitTaskInput[1];

        Event et = new Event(taskName, stringToDate(taskSchedule));
        tasks.add(et);

        return et;
    }

    /**
     * Adds an deadline task to save.
     *
     * @param deadline deadline to save.
     * @return deadline the deadline task that is saved.
     * @throws GnosisException If input is empty.
     */
    public Deadline addDeadline(String deadline) throws GnosisException {
        String[] splitTaskInput = deadline.split("/by");

        if (splitTaskInput.length != 2) {
            //deadline empty exception
            throw new GnosisException(GnosisConstants.DEADLINE_EMPTY_EXCEPT_MESSAGE);
        }

        String taskName = splitTaskInput[0];
        String taskDeadline = splitTaskInput[1];

        Deadline dl = new Deadline(taskName, stringToDate(taskDeadline));
        tasks.add(dl);

        return dl;
    }

    /**
     * Marks selected task as done.
     *
     * @param taskIndex taskIndex indicates task to mark done.
     * @return task Task that is marked done.
     * @throws GnosisException If taskIndex is out of bounds
     */
    public Task markTaskAsDone(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }

        tasks.get(taskIndex).setDone(true);
        return tasks.get(taskIndex);
    }

    /**
     * Deletes selected task.
     *
     * @param taskIndex taskIndex indicates which task to delete.
     * @return deadline the deadline task that is saved.
     * @throws GnosisException If taskIndex is out of bounds
     */
    public Task deleteTask(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }

        Task t = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        return t;
    }

    /**
     * Finds matching tasks with specified keyword
     *
     * @param taskKeyword Matching Keyword to filter tasks.
     * @return List of filtered task matching keyword.
     */
    public List<Task> findMatchingTasks(String taskKeyword) {
        // using stream to filter out only matching task in task list
        return this.tasks.stream()
                .filter(task -> task.getTaskName().toLowerCase().contains(taskKeyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getNumOfTasks() {
        return this.getTasks().size();
    }

    /**
     * Converts a string to a DateTime.
     *
     * @param dateString to convert to a date.
     * @return LocalDateTime formatted datetime.
     * @throws GnosisException If String date does not match date format.
     */
    public static LocalDateTime stringToDate(String dateString) throws GnosisException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(dateString.stripLeading(), formatter);
        } catch (DateTimeParseException e) {
            throw new GnosisException(GnosisConstants.DATETIME_FORMAT_EXCEPT_MESSAGE);
        }

        return ldt;
    }

}
