import java.util.ArrayList;
import java.util.List;
public class TaskHandler {
    private static final String NO_TASKS_FOUND = "Nothing here... :( Type to add something!";
    private List<Task> taskList;
    public TaskHandler() {
        taskList = new ArrayList<>();
    }
    public String addTask(Task task) {
        taskList.add(task);
        String str = String.format("added: %s", task.toString());
        return str;
    }
    public String printList() {
        String allTasks = "";
        if (taskList.size() == 0) {
            return NO_TASKS_FOUND;
        } else {
            for (int i  = 0; i < taskList.size(); i++) {
                int taskNumber =  i + 1;
                String taskName = taskList.get(i).toString();
                allTasks += String.format("%d. %s \n", taskNumber, taskName);
            }
        }
        return allTasks;
    }
}
