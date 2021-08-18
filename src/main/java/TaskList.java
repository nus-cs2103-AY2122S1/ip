import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String addToList(Task newTask) {
        tasks.add(newTask);
        return newTask.toString();
    }

    public String deleteFromList(int index) {
        Task curr = tasks.get(index - 1);
        tasks.remove(index - 1);
        return curr.toString();
    }

    public String taskDone(int index) {
        Task curr = tasks.get(index - 1);
        curr.markAsDone();
        return curr.toString();
    }

    public int taskCount() {
        return tasks.size();
    }

    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
                result = result + counter + "." + tasks.get(i) + "\n";
                counter++;

        }
        return result;
    }
}
