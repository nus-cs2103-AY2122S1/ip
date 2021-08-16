import java.util.ArrayList;
import java.lang.StringBuilder;
public class TaskList {
    private ArrayList<Task> userList;
    private int listSize = 0;
    public TaskList() {
        userList = new ArrayList<Task>(100);
    }

    public String getTasks() {
        if (listSize == 0) {
            return "The list is empty! *quack*";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i<=this.listSize; i++) {
            sb.append(i + ". " + userList.get(i - 1).getName() + "\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public String addTask(String taskName) {
        Task newTask = new Task(taskName);
        userList.add(newTask);
        listSize++;
        return "added: " + newTask.getName();
    }
}
