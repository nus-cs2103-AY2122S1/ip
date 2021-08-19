import java.util.ArrayList;

public class TaskManager {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(String task, String cmd) {
        Task t;
        if (cmd.equals("todo")) {
            t = new Todo(task);
            tasks.add(t);
        } else if (cmd.equals("deadline")) {
            String date = task.split(" /by ")[1];
            task = task.split(" /by ")[0];
            t = new Deadline(task, date);
            tasks.add(t);
        } else {
            String date = task.split(" /at ")[1];
            task = task.split(" /at ")[0];
            t = new Event(task, date);
            tasks.add(t);
        }
        Message.addTask(t);
    }

    public static void markDone(int taskId) {
        try {
            Task t = tasks.get(taskId-1);
            t.isDone = true;
            Message.done(t);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such task added!");
        }
    }
}
