package duke.task;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A list of a user's tasks.
 *
 * @author Aiken Wong
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }


    /**
     * Finds among its members, all tasks with dates that match the given date.
     *
     * @param date Date to match.
     * @return All tasks with matching dates.
     */
    public TaskList findByDate(LocalDate date) {

        TaskList foundTasks = new TaskList();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);

            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.startDateTime.toLocalDate().compareTo(date) <= 0 && event.endDateTime.toLocalDate()
                    .compareTo(date) >= 0) {
                    foundTasks.add(task);
                }

            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.date.toLocalDate().equals(date)) {
                    foundTasks.add(task);
                }
            }
        }

        return foundTasks;
    }

    /**
     * Finds among its members, all tasks with text that match the given text.
     *
     * @param input Text to match.
     * @return All tasks with matching text.
     */
    public TaskList findByDescription(String input) {

        TaskList foundTasks = new TaskList();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.description.contains(input)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }

    /**
     * Sorts the current tasks by date.
     */
    public void sortByDate() {
        this.tasks.sort(new TaskDateTimeComparator());
    }

    /**
     * Builds and returns the format string for storing current tasks.
     *
     * @return Storage String
     */
    public String getStorageString() {

        String text = "";

        for (int i = 0; i < tasks.size(); i++) {

            Task currentTask = tasks.get(i);
            if (currentTask instanceof Event) {
                Event event = (Event) currentTask;
                text += String.format("| E | %s | %s | %s | %s | %s\n", event.getIsDone() ? "X" : " ",
                    event.getIsDateOnly() ? "X" : " ", event.getStartDateTime(), event.getEndDateTime(),
                    event.getDescription());
            } else if (currentTask instanceof Deadline) {
                Deadline deadline = (Deadline) currentTask;
                text += String.format("| D | %s | %s | %s | %s\n", deadline.getIsDone() ? "X" : " ",
                    deadline.getIsDateOnly() ? "X" : " ", deadline.getDate(), deadline.getDescription());
            } else {
                Todo todo = (Todo) currentTask;
                text += String.format("| T | %s | %s\n", todo.getIsDone() ? "X" : " ", todo.getDescription());
            }
        }

        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof TaskList) {
            TaskList taskList = (TaskList) o;

            return this.tasks.equals(taskList.tasks);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.tasks.toString();
    }


}
