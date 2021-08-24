package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a task. Should not be used to instantiate Task object because Task is an abstract class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task. It is used for task that is already marked as done.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static Task convertToTask(String taskInfo) {
        String[] arr = taskInfo.split(" \\| ");
        char letter = arr[0].charAt(0);
        boolean isDone = arr[1].equals("X");
        switch (letter) {
            case 'T':
                return new Todo(arr[2], isDone);
            case 'D':
                LocalDate deadlineDate = LocalDate.parse(arr[3], Task.DATE_TIME_FORMATTER);
                LocalTime deadlineTime = LocalTime.parse(arr[4]);
                return new Deadline(arr[2], deadlineDate, deadlineTime, isDone);
            case 'E':
                LocalDate eventDate = LocalDate.parse(arr[3], Task.DATE_TIME_FORMATTER);
                String timeRange = arr[4];
                return new Event(arr[2], eventDate, timeRange, isDone);
            default:
                return null;
        }
    }

    public abstract String stringToStore();
}
