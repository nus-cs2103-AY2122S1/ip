import java.util.ArrayList;

public class Manager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void addReply(Task assignment) {
        tasks.add(assignment);
        System.out.println("Got it. I've added this task");
        System.out.println(assignment);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public static void listReply() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
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
}
