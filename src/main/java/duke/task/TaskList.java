package duke.task;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exception.DukeIoException;
import duke.exception.DukeTaskNumberOutOfBoundsException;
import duke.util.DateTimeUtils;
import duke.util.FileUtils;

/**
 * This is the TaskList class that contains a list of tasks
 * and the operations of the tasks.
 */
public class TaskList {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final int MILLIS_TO_HOUR_UNIT_CONVERSION = 1000 * 60 * 60;

    private final List<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns size of tasks.
     *
     * @return Tasks size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns task by number.
     *
     * @param number Number of task.
     * @return Task by number if exists, else empty.
     */
    public Task findTaskByNumber(int number) {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }

    /**
     * Completes task by number, start from 1.
     *
     * @param number Number of task.
     * @throws DukeTaskNumberOutOfBoundsException If task number is out of bounds.
     */
    public void completeTask(int number) throws DukeTaskNumberOutOfBoundsException {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNumberOutOfBoundsException("☹ OOPS!!! Task number is out of bounds.");
        }
        tasks.set(index, tasks.get(index).markAsDone());
    }

    /**
     * Deletes task by number, start from 1.
     *
     * @param number Number of task.
     * @throws DukeTaskNumberOutOfBoundsException If task number is out of bounds.
     */
    public Task deleteTask(int number) throws DukeTaskNumberOutOfBoundsException {
        int index = number - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskNumberOutOfBoundsException("☹ OOPS!!! Task number is out of bounds.");
        }
        return tasks.remove(index);
    }

    /**
     * Clears all tasks.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Prints tasks from TaskList.
     */
    public String[] printTasks() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
                        .collect(Collectors.toList()).toArray(String[]::new);
    }

    /**
     * Loads tasks from file with path: src/data/duke.txt.
     */
    public void loadTasksFromFile() throws DukeIoException {
        List<String> formattedTasks = FileUtils.loadFile(DIR_NAME, FILE_NAME);
        try {
            formattedTasks.forEach(formattedTask -> {
                String[] contents = formattedTask.split(" \\| ", 4);
                // Asserts that the task can be split to 3 or 4 parts by | symbol.
                assert contents.length >= 3 && contents.length <= 4
                        : "☹ OOPS!!! Task cannot be split to 3 or 4 parts by | symbol.";
                String taskID = contents[0];
                String isDone = contents[1];
                String name = contents[2];
                switch (taskID) {
                case Todo.ID:
                    addTodoTask(isDone, name);
                    break;
                case Deadline.ID:
                    // Asserts that the task can be split to 4 parts by | symbol.
                    assert contents.length == 4
                            : "☹ OOPS!!! Task cannot be split to 4 parts by | symbol.";
                    String byTime = contents[3];
                    addDeadlineTask(isDone, name, byTime);
                    break;
                case Event.ID:
                    // Asserts that the task can be split to 4 parts by | symbol.
                    assert contents.length == 4
                            : "☹ OOPS!!! Task cannot be split to 4 parts by | symbol.";
                    String atTime = contents[3];
                    addEventTask(isDone, name, atTime);
                    break;
                default:
                    break;
                }
            });
        } catch (Exception e) {
            throw new DukeIoException("☹ OOPS!!! Load tasks from file error.");
        }
    }

    private void addTodoTask(String isDone, String name) {
        Todo todo = new Todo(name, isDone.equals("0"));
        addTask(todo);
    }

    private void addDeadlineTask(String isDone, String name, String byTime) {
        Deadline deadline = new Deadline(
                name,
                DateTimeUtils.parseDateTime(byTime),
                isDone.equals("0")
        );
        addTask(deadline);
    }

    private void addEventTask(String isDone, String name, String atTime) {
        String[] dateTimes = atTime.split(" ", 2);
        String[] times = dateTimes[1].split("-", 2);
        // Asserts that the dateTimes can be split to 2 parts by space.
        // The first part is date, the second part is times.
        assert dateTimes.length == 2
                : "☹ OOPS!!! DateTimes cannot be split to 2 parts by space.";
        // Asserts that the times can be split to 2 parts by - symbol.
        // The first part is startTime, the second part is endTime.
        assert times.length == 2
                : "☹ OOPS!!! Times cannot be split to 2 parts by - symbol.";
        String atDate = dateTimes[0];
        String startTime = times[0];
        String endTime = times[1];
        EventDateTime eventDateTime = new EventDateTime(
                DateTimeUtils.parseDate(atDate),
                DateTimeUtils.parseTime(startTime),
                DateTimeUtils.parseTime(endTime)
        );
        Event event = new Event(name, eventDateTime, isDone.equals("0"));
        addTask(event);
    }

    /**
     * Saves tasks to file with path: src/data/duke.txt.
     */
    public void saveTasksToFile() throws DukeIoException {
        List<String> formattedTasks = tasks.stream()
                .map(task -> String.join(" | ", task.formatTask()))
                        .collect(Collectors.toList());
        boolean isSaved = FileUtils.isFileSaved(DIR_NAME, FILE_NAME, formattedTasks);
        if (!isSaved) {
            throw new DukeIoException("☹ OOPS!!! Save tasks to file error.");
        }
    }

    /**
     * Prints tasks which contains keyword from TaskList.
     *
     * @param keyword Keyword of task name.
     * @return String array of filtered tasks.
     */
    public String[] findTasks(String keyword) {
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getName().contains(keyword))
                        .collect(Collectors.toList());
        return IntStream.range(0, filteredTasks.size())
                .mapToObj(i -> (i + 1) + ". " + filteredTasks.get(i).toString())
                        .collect(Collectors.toList()).toArray(String[]::new);
    }

    /**
     * Prints tasks which is coming in hours.
     *
     * @param hour Hour range (current + hour) of coming tasks.
     * @return String array of coming tasks.
     */
    public String[] findComingTasks(int hour) {
        List<Task> filteredTasks = tasks.stream()
            .filter(task -> {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    long deadlineTimeMillis = deadline.getByDateTime()
                            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                    long millisDifference = deadlineTimeMillis - System.currentTimeMillis();
                    long hourDifference = millisDifference / MILLIS_TO_HOUR_UNIT_CONVERSION;
                    return millisDifference >= 0 && hourDifference < hour;
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    long eventTimeMillis = LocalDateTime.of(
                            event.getAtDateTime().getAtDate(),
                            event.getAtDateTime().getStartTime()
                    ).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                    long millisDifference = eventTimeMillis - System.currentTimeMillis();
                    long hourDifference = millisDifference / MILLIS_TO_HOUR_UNIT_CONVERSION;
                    return millisDifference >= 0 && hourDifference < hour;
                }
                return false;
            }).collect(Collectors.toList());
        return IntStream.range(0, filteredTasks.size())
            .mapToObj(i -> (i + 1) + ". " + filteredTasks.get(i).toString())
            .collect(Collectors.toList()).toArray(String[]::new);
    }
}
