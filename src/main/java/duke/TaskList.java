package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Encapsulates a List of Tasks and supports various functions related to managing the List of Tasks.
 */
public class TaskList {
    protected final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds task to the List.
     *
     * @param task The task to be added to the List.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets the task at given index.
     *
     * @param idx Index of task to get.
     * @return Task at the given index.
     */
    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    /**
     * Deletes task at given index.
     *
     * @param idx Index of task to be deleted.
     */
    public void removeTask(int idx) {
        taskList.remove(idx);
    }

    /**
     * Returns all Tasks in the TaskList that match a given keyword.
     *
     * @param keyword The given keyword.
     * @return A TaskList of all Tasks that match the given keyword.
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns if the task list is empty.
     *
     * @return If the task list is empty.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Clears all the tasks in the task list.
     */
    public void clearTasks() {
        taskList.clear();
    }

    private boolean isTaskBefore(Task task, LocalDate date) {
        if (task instanceof Deadline) {
            LocalDate deadlineDateBy = ((Deadline) task).getDateBy();
            return deadlineDateBy.isBefore(date) && !deadlineDateBy.isBefore(LocalDate.now());
        }
        if (task instanceof Event) {
            LocalDateTime eventTimeAt = ((Event) task).getTimeAt();
            return eventTimeAt.toLocalDate().isBefore(date) && !eventTimeAt.toLocalDate().isBefore(LocalDate.now());
        }
        return false;
    }

    /**
     * Returns a TaskList containing all tasks that are due/occur before the given date.
     *
     * @param date The date the task has to be due before.
     * @return A TaskList containing all tasks that are due/occur before the given date
     */
    public TaskList getTasksBefore(LocalDate date) {
        TaskList tasks = new TaskList();
        for (Task task : taskList) {
            if (isTaskBefore(task, date)) {
                tasks.addTask(task);
            }
        }
        return tasks;
    }

    @Override
    public String toString() {
        String lst = "";
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            lst += "\n" + i + ". " + task;
        }
        return lst;
    }

}
