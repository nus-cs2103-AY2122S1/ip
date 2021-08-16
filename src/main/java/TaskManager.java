import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    static private List<Task> taskList = new ArrayList<>();

    public static Task addTask(String rawTask) throws DukeException, IllegalArgumentException {
        Task ret = null;
        // parse raw task string
        String[] t = rawTask.split(" ", 2);
        String type = t[0];
        Optional<String> args = t.length > 1 ? Optional.of(t[1]) : Optional.empty();
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
                throw new InvalidDukeCommandException();
        }
        taskList.add(ret);

        return ret;
    }

    public static int taskCount() {
        return taskList.size();
    }

    public static Task completeTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            taskList.set(taskId, taskList.get(taskId).done());

            return taskList.get(taskId);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
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
