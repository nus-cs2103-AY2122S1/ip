import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskManager {
    static private List<Task> taskList = new ArrayList<>();

    public static Task addTask(String type, Optional<String> args) throws DukeException, IllegalArgumentException {
        Task ret = null;
        // parse raw task string
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

    public static Task deleteTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            return taskList.remove(taskId);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }

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
        return Stream.iterate(0, x -> x < taskList.size(), x -> x+1)
                .map(x -> String.format("%d. %s", x+1, taskList.get(x).toString()))
                .collect(Collectors.joining("\n"));
    }
}
