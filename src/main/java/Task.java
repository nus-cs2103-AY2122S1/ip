import java.io.IOException;
import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Category category;

    public enum Category {
        TODO, DEADLINE, EVENT
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static void createTask(String description, String time, Category category, boolean isDone, boolean hasNotif) {
        switch (category) {
        case TODO:
            Duke.list.add(new ToDo(description, isDone, hasNotif));
            break;
        case DEADLINE:
            Duke.list.add(new Deadline(description, time, isDone, hasNotif));
            break;
        case EVENT:
            Duke.list.add(new Event(description, time, isDone, hasNotif));
            break;
        }
    }

    public static void createTaskDate(String description, LocalDate time, Category category, boolean isDone, boolean hasNotif) {
        switch (category) {
        case TODO:
            Duke.list.add(new ToDo(description, isDone, hasNotif));
            break;
        case DEADLINE:
            Duke.list.add(new Deadline(description, time, isDone, hasNotif));
            break;
        case EVENT:
            Duke.list.add(new Event(description, time, isDone, hasNotif));
            break;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}