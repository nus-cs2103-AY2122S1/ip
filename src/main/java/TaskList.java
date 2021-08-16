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
            sb.append(
                    String.format(
                            "%d. %s  [%s]\n",
                            i,
                            userList.get(i - 1).getName(),
                            userList.get(i - 1).isCompleted() ? "X" : " "
                    )
            );
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

    public String markComplete(int taskID) {
        if (taskID - 1 < listSize) {
            if (userList.get(taskID - 1).completeTask()) {
                return String.format("You have completed task %d. %s", taskID, userList.get(taskID - 1).getName());
            } else {
                return "Something seems to have went terribly wrong";
            }
        } else {
            return String.format("Uh oh, seems like there is no task number %d", taskID);
        }
    }
}
