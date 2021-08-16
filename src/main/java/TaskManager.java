import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    static public List<Task> taskList = new ArrayList<>();

    public static boolean addTask(Task newTask) {
        return taskList.add(newTask);
    }

    public static String listTasks() {
        StringBuilder ret = new StringBuilder();
        for(int i=0; i < taskList.size(); i++) {
            ret.append(String.format("%d. %s\n", i+1, taskList.get(i).toString()));
        }
        return ret.toString();
    }
}
