import java.util.ArrayList;
import java.util.List;

public final class TaskList {
    private static List<String> taskString = new ArrayList<>();
    private static List<Tasks> taskList = new ArrayList<>();
    private static int taskNumber = 1;
    private final static String LINE = "-----------------------------------------";

    /**
     * Adds a task to the list and prints the addition.
     * @param task Task to be added
     */
    static void add(Tasks task) {
        taskString.add(taskNumber + ". ");
        taskList.add(task);
        taskNumber++;
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Marks a task as completed and prints this change.
     * @param task Task to complete
     */
    static void complete(String task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        String numString = task.replaceAll("[^0-9]", "");
        int num = Integer.parseInt(numString);
        for (int counter = 0; counter < taskString.size(); counter++) {
            if (counter == num - 1) {
                taskList.get(num - 1).markAsDone();
//                System.out.println(taskString.get(counter) + taskList.get(counter).toString());
            }
        }
        System.out.println(LINE);
    }

    /**
     * Prints the current task list.
     */
    static void printList() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        for (String str : taskString) {
            System.out.println(str + taskList.get(counter).toString());
            counter++;
        }
        System.out.println(LINE);
    }

    /**
     * Deletes an item from the taskList and taskString.
     * @param i Number of task to be deleted
     */
    public static void deleteTask(int i) {
        Tasks deletedTasks = taskList.get(i - 1);
        taskList.remove(i - 1);
        taskString.remove(i - 1);
        System.out.println("Noted. I've removed this task: \n" + deletedTasks + "\nNow you have " + taskString.size() + " tasks in the list.");
    }

    public static String overwrite() {
        String str = "";
        for (Tasks task : taskList) {
            str = task.toString() + "\n";
            System.out.println(task.toString());
        }
        return str;
    }
}
