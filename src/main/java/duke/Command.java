package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    /**
     * Add a task to the list.
     * @param task The string of the task.
     * @param type The type of the task: event, deadline, or other type.
     */
    public static String addTask(String task, Type type, LocalDateTime localDateTime, List<Task> tasks) {
        Task taskObj = TaskList.initialiseByType(task, type, false, localDateTime);
        tasks.add(taskObj);
        String addTaskString = String.format("Got it. I've added this task:\n"
                        + "%s"
                        + "\nNow you have %d tasks in the list.",
                taskObj.toString(), tasks.size());
        return addTaskString;
    }

    /**
     * Check task off as done in the list.
     * @param taskNumber index of task in list.
     */
    public static String doneTask(int taskNumber, List<Task> tasks) {
        taskNumber--;
        Task task = tasks.get(taskNumber);
        task.setDone(true);
        tasks.set(taskNumber, task);
        String doneTaskString = "Nice! I've marked this task as done:\n"
                + task.toString();
        return doneTaskString;
    }

    /**
     * Delete task from the list.
     * @param taskNumber index of task in list.
     */
    public static String deleteTask(int taskNumber, List<Task> tasks) {
        taskNumber--;
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        String deleteTaskString = String.format("Noted. I've removed this task:\n%s"
                        + "\nNow you have %d tasks in the list.",
                task.toString(), tasks.size());
        return deleteTaskString;
    }

    /**
     * Filter tasks out based on regex string.
     * @param regex String based on which to filter.
     * @param tasks duke.Task list from which to filter.
     * @return Filtered tasks.
     */
    public static List<Task> findTasks(String regex, List<Task> tasks) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getTask().contains(regex)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Sort events and deadlines by time
     * @param tasks List of all tasks.
     * @return Sorted task list.
     */
    public static List<Task> sortTasks(List<Task> tasks) {
        List<Task> sortedTasks = tasks
                .stream()
                .filter(task -> task.getDatetime() != null)
                .sorted(new Comparator<Task>() {
                    @Override
                    public int compare(Task task1, Task task2) {
                        if (task1.getDatetime().isAfter(task2.getDatetime())) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }).collect(Collectors.toList());
        return sortedTasks;
    }
}
