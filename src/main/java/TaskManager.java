import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> taskArrayList = new ArrayList<>();

    public void add(Task task) {
        taskArrayList.add(task);
    }

    public void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            String s = String.format("[%d] %s", i + 1, taskArrayList.get(i));
            System.out.println(s);
        }
    }
}
