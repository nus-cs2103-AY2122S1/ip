package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ui {

    /**
     * Method to add a task to our list.
     * @param task The string of the task.
     * @param type The type of the task: event, deadline, or other type.
     */
    public static void addTask(String task, Type type, LocalDateTime localDateTime, List<Task> tasks) {
        Task taskObj = TaskList.initialiseByType(task, type, false, localDateTime);
        tasks.add(taskObj);
        System.out.println(String.format("Got it. I've added this task:\n" + taskObj.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Method to check the task off as done in the list.
     * @param taskNumber The number of the task in our list.
     */
    public static void doneTask(int taskNumber, List<Task> tasks) {
        taskNumber--;
        Task task = tasks.get(taskNumber);
        task.setDone(true);
        tasks.set(taskNumber, task);
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Method to delete the task from the list.
     * @param taskNumber The number of the task in our list.
     */
    public static void deleteTask(int taskNumber, List<Task> tasks) {
        taskNumber--;
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Filter tasks out based on regex string.
     * @param regex String based on which to filter.
     * @param tasks duke.Task list from which to filter.
     * @return Filtered tasks.
     */
    public static List<Task> findTasks(String regex, List<Task> tasks) {
        List<Task> filteredTasks = new ArrayList<>();
        for(Task task: tasks) {
            if(task.getTask().contains(regex)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

}
