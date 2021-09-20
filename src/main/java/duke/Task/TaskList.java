package duke.Task;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public TaskList find(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>(tasks.stream()
                .filter(task -> task.contains(keyword))
                .collect(Collectors.toList()));
        TaskList tasksWithKeyword = new TaskList(filteredTasks);
        return tasksWithKeyword;
    }

    @Override
    public String toString() {
        if(tasks == null) {
            return "Hello";
        }
        String tasksInString = tasks.stream().map(Task::toString).reduce("",
                (x, y) -> x + "\n" + y);
        return tasksInString;
    }
    public String formattedToString() {
        int numberOfTasks = tasks.size();
        String tasksInStringFormatted = "";
        for (int i = 1; i <= numberOfTasks; i++) {
            tasksInStringFormatted = tasksInStringFormatted + i + ". " + tasks.get(i - 1) + "\n";
        }
        return tasksInStringFormatted;
    }

}
