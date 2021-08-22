package duke.logic;

import duke.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The logic for printing tasks.
 */
public final class LPrintTask {
    // No initialization required.
    private LPrintTask() {}

    /**
     * Prints all upcoming tasks from a list of tasks.
     *
     * @param tasks The list of tasks
     */
    public static void printUpcomingTasks(List<? extends Task> tasks) {
        int taskSize = tasks.size();
        Map<Task, Integer> upcomingTasks = new TreeMap<>((task1, task2) -> {
            LocalDateTime dateTime1 = task1.getDateTime();
            LocalDateTime dateTime2 = task2.getDateTime();
            // time can be null if task is to-do. By default, put all to-do to the last.
            return dateTime1 == null && dateTime2 == null
                    ? 0
                    : dateTime1 == null
                    ? 1
                    : dateTime2 == null
                    ? -1
                    : dateTime1.compareTo(dateTime2);
        });
        for (int i = 1; i <= taskSize; i++) {
            Task task = tasks.get(i - 1);
            String type = task.getTaskType();
            if (!task.isDone() && // Task is not done and it is either to-do or the date is later than now.
                    (type.equals("T") || task.getDateTime().isAfter(LocalDateTime.now()))) {
                upcomingTasks.put(task, i);
            }
        }
        upcomingTasks.forEach((task, i) -> print(task, i, taskSize));
    }

    /**
     * Prints all tasks from a list of tasks.
     *
     * @param tasks The list of tasks
     */
    public static void printAllTasks(List<? extends Task> tasks) {
        int taskSize = tasks.size();
        for (int i = 1; i <= taskSize; i++) {
            Task task = tasks.get(i - 1);
            print(task, i, taskSize);
        }
    }

    private static void print(Task task, int number, int max) {
        String leadingSpace = " ".repeat((int) Math.log10(max) - (int) Math.log10(number));
        // For better formatting if numbers exceed 9
        System.out.printf("%s%d: %s\n", leadingSpace, number, task);
    }

}
