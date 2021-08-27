package skeltal;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void addReply(Task assignment) {
        tasks.add(assignment);
        System.out.println("Got it. I've added this task");
        System.out.println(assignment);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public static void listReply() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public static int getIndex(Task task) {
        return tasks.indexOf(task);
    }

    public static void done(String index_1) {
        int i = Integer.parseInt(index_1) - 1;
        Task assignment = tasks.get(i);
        assignment.setComplete();
        System.out.println("Done! I've marked this task as done!");
        System.out.println(assignment);

    }

    public static void delete(String index) {
        int i = Integer.parseInt(index) - 1;
        System.out.println(tasks.get(i));
        tasks.remove(i);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void loadTaskList(ArrayList<Task> arrayList) {
        tasks = arrayList;
    }

    public static void findMatchingTasks(String str) {
        System.out.println("Here are the matching tasks in your list.");
        for (Task task : tasks) {
            if (task.getTask().contains(str)) {
                System.out.println(task);
            }
        }
    }

    public static String storeTasks() {
        String toWrite = "";
        for (Task task : tasks) {
            toWrite += task.store() + "\n";
        }
        return toWrite;
    }
}

