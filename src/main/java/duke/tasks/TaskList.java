package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public final class TaskList {
    private static final List<String> taskString = new ArrayList<>();
    private static final List<Tasks> taskList = new ArrayList<>();
    private static int taskNumber = 1;

    /**
     * Adds a task to the list and returns the addition.
     * @param task Task to be added
     */
    public static String addTask(Tasks task) {
        taskString.add(taskNumber + ". ");
        taskList.add(task);
        taskNumber++;
        return "Got it. I've added this task: \n" + task
                + "\nNow you have " + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Adds a specific task according to the correct class
     * @param task task to be added
     */
    public static String addSpecificTask(String task) {
        String str = "";
        if (task.startsWith("deadline")) {
            String[] input = task.split(" /by ", 2);
            LocalDateTime dueTime = LocalDateTime.parse(input[1], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            str += TaskList.addTask(new Deadline(task, dueTime));
        } else if (task.startsWith("event") && task.contains("/at ")) {
            String[] input = task.split(" /at ", 2);
            LocalDateTime startTime = LocalDateTime.parse(input[1], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            str += TaskList.addTask(new Event(task, startTime));
        } else if (task.startsWith("todo")) {
            String[] input = task.split("todo ", 2);
            str += TaskList.addTask(new Todo(input[1]));
        }
        return str;
    }

    /**
     * Marks a task as completed and prints this change.
     * @param task Task to complete
     */
    public static String complete(String task) {
        String str = "Nice! I've marked this task as done: \n";
        String numString = task.replaceAll("[^0-9]", "");
        int num = Integer.parseInt(numString);
        for (int counter = 0; counter < taskString.size(); counter++) {
            if (counter == num - 1) {
                taskList.get(num - 1).markAsDone();
                int index = counter + 1;
                str += index + ". " + taskList.get(num - 1).toString();
            }
        }
        return str;
    }

    /**
     * Prints the current task list.
     */
    public static String printList() {
        String list = "Here are the tasks in your list:";
        int counter = 0;
        for (String str : taskString) {
            list += "\n" + str + taskList.get(counter).toString();
            counter++;
        }
        return list;
    }

    /**
     * Deletes an item from the taskList and taskString.
     * @param i Index of task to be deleted
     */
    public static String deleteTask(int i) {
        Tasks deletedTasks = taskList.get(i - 1);
        taskList.remove(i - 1);
        taskString.remove(i - 1);
        return "Noted. I've removed this task: \n" + deletedTasks
                + "\nNow you have " + taskString.size() + " tasks in the list.";
    }

    /**
     * Rewrites the duke.txt file with the new list of tasks.
     * @return a String representation of the task list
     */
    public static String overwrite() {
        String str = "";
        for (Tasks task : taskList) {
            str = task.toString() + "\n";
        }
        return str;
    }

    /**
     * Gets the most recent task.
     * @return most recent Task
     */
    public static Tasks getLast() {
        int size = taskList.size();
        return taskList.get(size - 1);
    }

    public static int getSize() {
        return taskList.size();
    }

    /**
     * Prints the tasks containing a keyword that the user has searched.
     * @param key keyword being searched.
     */
    public static String find(String key) {
        String str = "Here are the matching tasks in your list:";
        int count = 1;
        for (Tasks task : taskList) {
            if (task.toString().contains(key)) {
                str += "\n" + count + ". " + task;
                count++;
            }
        }
        return str;
    }

    public static int getSize() {
        return taskList.size();
    }
}
