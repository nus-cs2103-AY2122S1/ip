import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    static private List<Task> taskList = new ArrayList<>();

    public static Task addTask(String rawTask) {
        Task ret = null;
        // parse raw task string
        String[] t = rawTask.split(" ", 2);
        String type = t[0];
        String args = t[1];

        switch (type) {
            case "todo":
                ret = ToDo.of(args);
                break;
            case "deadline":
                ret = Deadline.of(args);
                break;
            case "event":
                ret = Event.of(args);
                break;
            default:
                ret = new Task(rawTask);
                break;
        }
        taskList.add(ret);

        return ret;
    }

    public static int taskCount() {
        return taskList.size();
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
