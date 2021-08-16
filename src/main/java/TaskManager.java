import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    static public List<Task> taskList = new ArrayList<>();

    public static boolean addTask(Task newTask) {
        return taskList.add(newTask);
    }

    public static String completeTask(int taskId) {
        if (taskId < taskList.size()) {
            taskList.set(taskId, taskList.get(taskId).done());

            return String.format(
                    "Nice! I've marked this task as done: \n" +
                    "%s", taskList.get(taskId).toString());
        } else {
            return "complete task failed, taskId > taskListSize";
        }
    }

    public static String listTasks() {
        StringBuilder ret = new StringBuilder();
        ret.append("Here are the tasks in your list:\n");
        for(int i=0; i < taskList.size(); i++) {
            ret.append(String.format("%d. %s\n", i+1, taskList.get(i).toString()));
        }
        return ret.toString();
    }
}
