import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final List<Task> taskList = new ArrayList<>();

    public static int getCount() {
        return taskList.size();
    }

    public static Task get(int index) {
        return taskList.get(index);
    }

    public static void addTodo(String item) {
        taskList.add(new ToDo(item));
    }

    public static void addDeadline(String item, String by) throws IrisException {
        taskList.add(new Deadline(item, by));
    }

    public static void addEvent(String item, String at) throws IrisException {
        taskList.add(new Event(item, at));
    }

    private static void validateTaskIndex(int index) throws IrisException {
        if (index <= 0) throw new IrisException("Please enter a valid task index.");
        int count = taskList.size();
        if (index > count) throw new IrisException(String.format("Your task list only has %d items", count));
    }

    public static Task done(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = taskList.get(index - 1);
        task.markComplete();
        return task;
    }

    public static Task delete(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        return task;
    }

    public static String[] toCommands() {
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            commands.add(taskList.get(i).toCommand(i + 1));
        }
        String[] result = new String[commands.size()];
        return commands.toArray(result);
    }
}
