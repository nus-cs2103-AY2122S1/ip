import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> taskArrayList = new ArrayList<>();

    public void add(Task task) {
        taskArrayList.add(task);
    }

    public void list() {
        System.out.println("Duke says >> ");
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task t = taskArrayList.get(i);
            String isDone = t.isDone() ? "x" : " ";
            System.out.println(String.format("%d.[%s] %s", i + 1, isDone, t));
        }
        System.out.println("");
    }
}
