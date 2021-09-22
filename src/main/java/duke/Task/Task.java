package duke.Task;

import java.time.LocalDate;

/**
 * Class describing the task object
 */
public class Task implements Comparable<Task>{
    private String taskDescription;
    private boolean isDone;
    private LocalDate date = LocalDate.parse("0000-01-01");

    public Task(String description, boolean isDone) {
        this.taskDescription = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean contains(String keyword) {
        return this.taskDescription.contains(keyword);
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " ";
        return String.format("[%s] %s", done, taskDescription);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public int compareTo(Task o) {
        return this.getDate().compareTo(o.getDate());
    }
}
